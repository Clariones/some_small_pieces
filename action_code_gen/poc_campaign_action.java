    // //////////////////// below are methods for action customerRegisterCampaign ///////////////////
    // Entry point for action customerRegisterCampaign
    public Object actionCustomerRegisterCampaign(ShuxiangUserContext userContext,String customerId ,String campaignId) throws Exception{
        Customer currentCustomer = userContext.getDAOGroup().getCustomerDAO().load(customerId, CustomerTokens.withoutLists());
        Campaign currentCampaign = userContext.getDAOGroup().getCampaignDAO().load(campaignId, CampaignTokens.withoutLists());

        SmartList<ActionToken> usedTokens = checkActionCustomerRegisterCampaign(userContext,currentCustomer ,currentCampaign);

        Map<String, BaseEntity> touchedObjects = executeActionCustomerRegisterCampaign(userContext,currentCustomer ,currentCampaign, usedTokens);
        saveCustomerRegisterCampaignTouchedObjects(userContext,currentCustomer ,currentCampaign, usedTokens, touchedObjects);
        sendCustomerRegisterCampaignNotifications(userContext,currentCustomer, currentCampaign,usedTokens, touchedObjects);
        return constructCustomerRegisterCampaignResult(userContext,currentCustomer ,currentCampaign, touchedObjects);
    }
    // check conditions for action customerRegisterCampaign
    protected SmartList<ActionToken> checkActionCustomerRegisterCampaign(ShuxiangUserContext userContext ,Customer currentCustomer ,Campaign currentCampaign) throws Exception{
        // check input actors are valid
        if (currentCustomer == null){
            throw new CampaignManagerException("未指定有效的用户");
        }
        if (currentCampaign == null){
            throw new CampaignManagerException("未指定有效的活动");
        }

        SmartList<ActionToken> neededTokens = new SmartList<ActionToken>();
        SmartList<ActionToken> tokens = null;
        return neededTokens;
    }
    // save affected objects in the "customerRegisterCampaign"
    // 重载办法：重载该函数，先把需要自定义保存行为的对象，使用 touchedObjects.remove("角色名字") 移除，
    // 然后调用super.saveCustomerRegisterCampaignTouchedObjects，以及自己写的保存代码。
    protected void saveCustomerRegisterCampaignTouchedObjects(ShuxiangUserContext userContext,Customer currentCustomer ,Campaign currentCampaign, SmartList<ActionToken> usedTokens, Map<String, BaseEntity> touchedObjects)throws Exception{
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
        object = touchedObjects.get("record");
        if (object != null){
            userContext.getDAOGroup().getCampaignRegisterHistoryDAO().save((CampaignRegisterHistory)object, CampaignRegisterHistoryTokens.empty());
        }
    }
    // send notifications of action CustomerRegisterCampaign
    protected void sendCustomerRegisterCampaignNotifications(ShuxiangUserContext userContext,Customer currentCustomer, Campaign currentCampaign, SmartList<ActionToken> usedTokens, Map<String, BaseEntity>touchedObjects)throws Exception{
        // by default, no any notification was sent.
    }
    // construct the result view-model of the action customerRegisterCampaign
    protected Object constructCustomerRegisterCampaignResult(ShuxiangUserContext userContext,Customer currentCustomer ,Campaign currentCampaign, Map<String, BaseEntity>touchedObjects)throws Exception{
        // TODO 你必须自己构建action的结果视图对象，touchedObjects中的【名字：类型】对应如下：
        // record : CampaignRegisterHistory
        throw new UnsupportedOperationException("你必须自己实现constructCustomerRegisterCampaignResult方法。");
    }
    // The actual execution of the action customerRegisterCampaign
    protected Map<String, BaseEntity> executeActionCustomerRegisterCampaign(ShuxiangUserContext userContext,Customer currentCustomer ,Campaign currentCampaign, SmartList<ActionToken> usedTokens)throws Exception{
        // TODO 你必须自己实现action的过程，输出结果注意必须符合以下【名字：类型】
        // record : CampaignRegisterHistory
        throw new UnsupportedOperationException("你必须自己实现executeActionCustomerRegisterCampaign方法。");
    }
    // //////////////// Code done for action customerRegisterCampaign ////////////////

    // //////////////////// below are methods for action customerUnregisterCampaign ///////////////////
    // Entry point for action customerUnregisterCampaign
    public Object actionCustomerUnregisterCampaign(ShuxiangUserContext userContext,String customerId ,String campaignId) throws Exception{
        Customer currentCustomer = userContext.getDAOGroup().getCustomerDAO().load(customerId, CustomerTokens.withoutLists());
        Campaign currentCampaign = userContext.getDAOGroup().getCampaignDAO().load(campaignId, CampaignTokens.withoutLists());

        SmartList<ActionToken> usedTokens = checkActionCustomerUnregisterCampaign(userContext,currentCustomer ,currentCampaign);

        Map<String, BaseEntity> touchedObjects = executeActionCustomerUnregisterCampaign(userContext,currentCustomer ,currentCampaign, usedTokens);
        saveCustomerUnregisterCampaignTouchedObjects(userContext,currentCustomer ,currentCampaign, usedTokens, touchedObjects);
        sendCustomerUnregisterCampaignNotifications(userContext,currentCustomer, currentCampaign,usedTokens, touchedObjects);
        return constructCustomerUnregisterCampaignResult(userContext,currentCustomer ,currentCampaign, touchedObjects);
    }
    // check conditions for action customerUnregisterCampaign
    protected SmartList<ActionToken> checkActionCustomerUnregisterCampaign(ShuxiangUserContext userContext ,Customer currentCustomer ,Campaign currentCampaign) throws Exception{
        // check input actors are valid
        if (currentCustomer == null){
            throw new CampaignManagerException("未指定有效的用户");
        }
        if (currentCampaign == null){
            throw new CampaignManagerException("未指定有效的活动");
        }

        SmartList<ActionToken> neededTokens = new SmartList<ActionToken>();
        SmartList<ActionToken> tokens = null;
        return neededTokens;
    }
    // save affected objects in the "customerUnregisterCampaign"
    // 重载办法：重载该函数，先把需要自定义保存行为的对象，使用 touchedObjects.remove("角色名字") 移除，
    // 然后调用super.saveCustomerUnregisterCampaignTouchedObjects，以及自己写的保存代码。
    protected void saveCustomerUnregisterCampaignTouchedObjects(ShuxiangUserContext userContext,Customer currentCustomer ,Campaign currentCampaign, SmartList<ActionToken> usedTokens, Map<String, BaseEntity> touchedObjects)throws Exception{
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
        object = touchedObjects.get("record");
        if (object != null){
            userContext.getDAOGroup().getCampaignRegisterHistoryDAO().save((CampaignRegisterHistory)object, CampaignRegisterHistoryTokens.empty());
        }
    }
    // send notifications of action CustomerUnregisterCampaign
    protected void sendCustomerUnregisterCampaignNotifications(ShuxiangUserContext userContext,Customer currentCustomer, Campaign currentCampaign, SmartList<ActionToken> usedTokens, Map<String, BaseEntity>touchedObjects)throws Exception{
        // by default, no any notification was sent.
    }
    // construct the result view-model of the action customerUnregisterCampaign
    protected Object constructCustomerUnregisterCampaignResult(ShuxiangUserContext userContext,Customer currentCustomer ,Campaign currentCampaign, Map<String, BaseEntity>touchedObjects)throws Exception{
        // TODO 你必须自己构建action的结果视图对象，touchedObjects中的【名字：类型】对应如下：
        // record : CampaignRegisterHistory
        throw new UnsupportedOperationException("你必须自己实现constructCustomerUnregisterCampaignResult方法。");
    }
    // The actual execution of the action customerUnregisterCampaign
    protected Map<String, BaseEntity> executeActionCustomerUnregisterCampaign(ShuxiangUserContext userContext,Customer currentCustomer ,Campaign currentCampaign, SmartList<ActionToken> usedTokens)throws Exception{
        // TODO 你必须自己实现action的过程，输出结果注意必须符合以下【名字：类型】
        // record : CampaignRegisterHistory
        throw new UnsupportedOperationException("你必须自己实现executeActionCustomerUnregisterCampaign方法。");
    }
    // //////////////// Code done for action customerUnregisterCampaign ////////////////

    // //////////////////// below are methods for action customerReviewCampaign ///////////////////
    // Entry point for action customerReviewCampaign
    public Object actionCustomerReviewCampaign(ShuxiangUserContext userContext,String customerId ,String campaignId ,String reviewContent) throws Exception{
        Customer currentCustomer = userContext.getDAOGroup().getCustomerDAO().load(customerId, CustomerTokens.withoutLists());
        Campaign currentCampaign = userContext.getDAOGroup().getCampaignDAO().load(campaignId, CampaignTokens.withoutLists());

        SmartList<ActionToken> usedTokens = checkActionCustomerReviewCampaign(userContext,currentCustomer ,currentCampaign ,reviewContent);

        Map<String, BaseEntity> touchedObjects = executeActionCustomerReviewCampaign(userContext,currentCustomer ,currentCampaign ,reviewContent, usedTokens);
        saveCustomerReviewCampaignTouchedObjects(userContext,currentCustomer ,currentCampaign, usedTokens, touchedObjects);
        sendCustomerReviewCampaignNotifications(userContext,currentCustomer, currentCampaign,usedTokens, touchedObjects);
        return constructCustomerReviewCampaignResult(userContext,currentCustomer ,currentCampaign, touchedObjects);
    }
    // check conditions for action customerReviewCampaign
    protected SmartList<ActionToken> checkActionCustomerReviewCampaign(ShuxiangUserContext userContext ,Customer currentCustomer ,Campaign currentCampaign ,String reviewContent) throws Exception{
        // check input actors are valid
        if (currentCustomer == null){
            throw new CampaignManagerException("未指定有效的用户");
        }
        if (currentCampaign == null){
            throw new CampaignManagerException("未指定有效的活动");
        }

        SmartList<ActionToken> neededTokens = new SmartList<ActionToken>();
        SmartList<ActionToken> tokens = null;
        return neededTokens;
    }
    // save affected objects in the "customerReviewCampaign"
    // 重载办法：重载该函数，先把需要自定义保存行为的对象，使用 touchedObjects.remove("角色名字") 移除，
    // 然后调用super.saveCustomerReviewCampaignTouchedObjects，以及自己写的保存代码。
    protected void saveCustomerReviewCampaignTouchedObjects(ShuxiangUserContext userContext,Customer currentCustomer ,Campaign currentCampaign, SmartList<ActionToken> usedTokens, Map<String, BaseEntity> touchedObjects)throws Exception{
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
        object = touchedObjects.get("review");
        if (object != null){
            userContext.getDAOGroup().getCampaignReviewDAO().save((CampaignReview)object, CampaignReviewTokens.empty());
        }
    }
    // send notifications of action CustomerReviewCampaign
    protected void sendCustomerReviewCampaignNotifications(ShuxiangUserContext userContext,Customer currentCustomer, Campaign currentCampaign, SmartList<ActionToken> usedTokens, Map<String, BaseEntity>touchedObjects)throws Exception{
        // by default, no any notification was sent.
    }
    // construct the result view-model of the action customerReviewCampaign
    protected Object constructCustomerReviewCampaignResult(ShuxiangUserContext userContext,Customer currentCustomer ,Campaign currentCampaign, Map<String, BaseEntity>touchedObjects)throws Exception{
        // TODO 你必须自己构建action的结果视图对象，touchedObjects中的【名字：类型】对应如下：
        // review : CampaignReview
        throw new UnsupportedOperationException("你必须自己实现constructCustomerReviewCampaignResult方法。");
    }
    // The actual execution of the action customerReviewCampaign
    protected Map<String, BaseEntity> executeActionCustomerReviewCampaign(ShuxiangUserContext userContext,Customer currentCustomer ,Campaign currentCampaign ,String reviewContent, SmartList<ActionToken> usedTokens)throws Exception{
        // TODO 你必须自己实现action的过程，输出结果注意必须符合以下【名字：类型】
        // review : CampaignReview
        throw new UnsupportedOperationException("你必须自己实现executeActionCustomerReviewCampaign方法。");
    }
    // //////////////// Code done for action customerReviewCampaign ////////////////

