<#list actions as action>
    <#assign exceptionName="Exception"/>
    // //////////////////// below are methods for action ${action.name} ///////////////////
    // Entry point for action ${action.name}
    public Object action${action.name?cap_first}(${user_context?cap_first} userContext<@compress single_line=true>
        <#list action.actors as actor>
            ,String ${actor?uncap_first}Id
        </#list>
        <#if action.inputs?has_content>
            <#list action.inputs as input>
                ,${input.type_name} ${input.role_name}
            </#list>
        </#if>
    </@compress>) throws ${exceptionName}{
    <#list action.actors as actor>
        <#assign Actor = actor?cap_first/>
        ${Actor} current${Actor} = userContext.getDAOGroup().get${Actor}DAO().load(${actor?uncap_first}Id, ${Actor}Tokens.withoutLists());
    </#list>

        SmartList<ActionToken> usedTokens = checkAction${action.name?cap_first}(userContext<@compress single_line=true>
            <#list action.actors as actor>
                ,current${actor?cap_first}
            </#list>
            <#if action.inputs?has_content>
                <#list action.inputs as input>
                    ,${input.role_name}
                </#list>
            </#if>
        </@compress>);

        Map<String, BaseEntity> touchedObjects = executeAction${action.name?cap_first}(userContext<@compress single_line=true>
            <#list action.actors as actor>
                ,current${actor?cap_first}
            </#list>
            <#if action.inputs?has_content>
                <#list action.inputs as input>
                    ,${input.role_name}
                </#list>
            </#if>
            </@compress>, usedTokens);
        save${action.name?cap_first}TouchedObjects(userContext<@compress single_line=true>
            <#list action.actors as actor>
                ,current${actor?cap_first}
            </#list></@compress>, usedTokens, touchedObjects);
        send${action.name?cap_first}Notifications(userContext,<@compress single_line=true>
            <#list action.actors as actor>
                current${actor?cap_first},
            </#list>
        </@compress>usedTokens, touchedObjects);
        return construct${action.name?cap_first}Result(userContext<@compress single_line=true>
            <#list action.actors as actor>
                ,current${actor?cap_first}
            </#list>
        </@compress>, touchedObjects);
    }
    // check conditions for action ${action.name}
    protected SmartList<ActionToken> checkAction${action.name?cap_first}(${user_context?cap_first} userContext <@compress single_line=true>
            <#list action.actors as actor>
                ,${actor?cap_first} current${actor?cap_first}
            </#list>
            <#if action.inputs?has_content>
                <#list action.inputs as input>
                    ,${input.type_name} ${input.role_name}
                </#list>
            </#if>
        </@compress>) throws ${exceptionName}{
        // check input actors are valid
    <#list action.actors as actor>
        <#assign Actor = actor?cap_first/>
        if (current${actor?cap_first} == null){
            throw new ${action.manager?cap_first}ManagerException("未指定有效的${action.diction[actor]!actor}");
        }
    </#list>

        SmartList<ActionToken> neededTokens = new SmartList<ActionToken>();
        SmartList<ActionToken> tokens = null;
    <#if action.comsume_tokens?has_content>
        <#list action.comsume_tokens?keys as actorName>
            <#list action.comsume_tokens[actorName] as tokenName>
        tokens = userContext.getManagerGroup().getActionTokenManager().findTokensByActor(userContext, current${actorName?cap_first}, "${tokenName}");
        if (!filterUsableToken(tokens)){
            throw new ${action.manager?cap_first}ManagerException("${action.diction[actorName]!actorName}没有${action.diction[tokenName]!tokenName}的权限");
        }
        neededTokens.addAll(tokens);
            </#list>
        </#list>
    </#if>
    <#if action.produce_tokens?has_content>
        <#list action.produce_tokens?keys as actorName>
            <#list action.produce_tokens[actorName] as tokenName>
        tokens = userContext.getManagerGroup().getActionTokenManager().findTokensByActor(userContext, current${actorName?cap_first}, "${tokenName}");
        if (filterValidToken(tokens)){
            neededTokens.addAll(tokens);
        }
            </#list>
        </#list>
    </#if>
        return neededTokens;
    }
    // save affected objects in the "${action.name}"
    // 重载办法：重载该函数，先把需要自定义保存行为的对象，使用 touchedObjects.remove("角色名字") 移除，
    // 然后调用super.save${action.name?cap_first}TouchedObjects，以及自己写的保存代码。
    protected void save${action.name?cap_first}TouchedObjects(${user_context?cap_first} userContext<@compress single_line=true>
            <#list action.actors as actor>
                ,${actor?cap_first} current${actor?cap_first}
            </#list></@compress>, SmartList<ActionToken> usedTokens, Map<String, BaseEntity> touchedObjects)throws ${exceptionName}{
        if(usedTokens != null && !usedTokens.isEmpty()){
            for(ActionToken token : usedTokens){
                userContext.getDAOGroup().getActionTokenDAO().save(token, ActionTokenTokens.empty());
            }
        }
        if (touchedObjects == null || touchedObjects.isEmpty()){
            return;
        }
        BaseEntity object = null;
        // 这里使用 !=null 而不是 instanceof 判断，是为了及早暴露逻辑错误
    <#list action.touched_objects as touched>
        object = touchedObjects.get("${touched.role_name}");
        if (object != null){
            ${touched.type_name?cap_first} ${touched.type_name?uncap_first}= (${touched.type_name?cap_first})object;
            boolean isNewCreate = ${touched.type_name?uncap_first}.getVersion() == 0;
            userContext.getDAOGroup().get${touched.type_name?cap_first}DAO().save(${touched.type_name?uncap_first}, ${touched.type_name?cap_first}Tokens.${touched.scope_token});
            if (isNewCreate){
                userContext.getManagerGroup().get${touched.type_name?cap_first}Manager().onNewInstanceCreated(userContext, ${touched.type_name?uncap_first});
            }
        }
    </#list>
    }
    // send notifications of action ${action.name?cap_first}
    protected void send${action.name?cap_first}Notifications(${user_context?cap_first} userContext,<@compress single_line=true>
            <#list action.actors as actor>
                ${actor?cap_first} current${actor?cap_first},
            </#list>
        </@compress> SmartList<ActionToken> usedTokens, Map<String, BaseEntity>touchedObjects)throws ${exceptionName}{
        // by default, no any notification was sent.
    }
    // construct the result view-model of the action ${action.name}
    protected Object construct${action.name?cap_first}Result(${user_context?cap_first} userContext<@compress single_line=true>
            <#list action.actors as actor>
                ,${actor?cap_first} current${actor?cap_first}
            </#list></@compress>, Map<String, BaseEntity>touchedObjects)throws ${exceptionName}{
        // TODO 你必须自己构建action的结果视图对象，touchedObjects中的【名字：类型】对应如下：
    <#list action.touched_objects as touched>
        // ${touched.role_name} : ${touched.type_name?cap_first}
    </#list>
        throw new UnsupportedOperationException("你必须自己实现construct${action.name?cap_first}Result方法。");
    }
    // The actual execution of the action ${action.name}
    protected Map<String, BaseEntity> executeAction${action.name?cap_first}(${user_context?cap_first} userContext<@compress single_line=true>
            <#list action.actors as actor>
                ,${actor?cap_first} current${actor?cap_first}
            </#list>
            <#if action.inputs?has_content>
                <#list action.inputs as input>
                    ,${input.type_name} ${input.role_name}
                </#list>
            </#if>
            </@compress>, SmartList<ActionToken> usedTokens)throws ${exceptionName}{
        // TODO 你必须自己实现action的过程，输出结果注意必须符合以下【名字：类型】
    <#list action.touched_objects as touched>
        // ${touched.role_name} : ${touched.type_name?cap_first}
    </#list>
        throw new UnsupportedOperationException("你必须自己实现executeAction${action.name?cap_first}方法。");
    }
    // //////////////// Code done for action ${action.name} ////////////////

</#list>