    // //////////////////// below are methods for action customerBorrowBook ///////////////////
    // Entry point for actino customerBorrowBook
    public Object actionCustomerBorrowBook(ShuxiangUserContext userContext,String customerId ,String bookCopyId) throws Exception{
        Customer currentCustomer = userContext.getDAOGroup().getCustomerDAO().load(customerId, CustomerTokens.withoutLists());
        BookCopy currentBookCopy = userContext.getDAOGroup().getBookCopyDAO().load(bookCopyId, BookCopyTokens.withoutLists());

        SmartList<ActionToken> usedTokens = checkActionCustomerBorrowBook(userContext,currentCustomer ,currentBookCopy);

        Map<String, BaseEntity> touchedObjects = executeActionCustomerBorrowBook(userContext,currentCustomer ,currentBookCopy, usedTokens);
        saveCustomerBorrowBookTouchedObjects(userContext,currentCustomer ,currentBookCopy, usedTokens, touchedObjects);
        sendCustomerBorrowBookNotifications(userContext,currentCustomer, currentBookCopy,usedTokens, touchedObjects);
        return constructCustomerBorrowBookResult(userContext,currentCustomer ,currentBookCopy, touchedObjects);
    }
    // check conditions for action customerBorrowBook
    protected SmartList<ActionToken> checkActionCustomerBorrowBook(ShuxiangUserContext userContext, Customer currentCustomer, BookCopy currentBookCopy) throws Exception{
        // check input actors are valid
        if (currentCustomer == null){
            throw new BookCopyManagerException("未指定有效的用户");
        }
        if (currentBookCopy == null){
            throw new BookCopyManagerException("未指定有效的借阅图书");
        }

        SmartList<ActionToken> neededTokens = new SmartList<ActionToken>();
        SmartList<ActionToken> tokens = null;
        tokens = userContext.getManagerGroup().getActionTokenManager().findTokensByActor(userContext, currentCustomer, "BORROW_BOOK");
        if (!filterUsableToken(tokens)){
            throw new BookCopyManagerException("用户没有借阅图书的权限");
        }
        neededTokens.addAll(tokens);
        return neededTokens;
    }
    // The actual execution of the action customerBorrowBook
    protected Map<String, BaseEntity> executeActionCustomerBorrowBook(ShuxiangUserContext userContext,Customer currentCustomer ,BookCopy currentBookCopy, SmartList<ActionToken> usedTokens)throws Exception{
        // TODO 你必须自己实现action的过程，输出结果注意必须符合以下【名字：类型】
        // record : BorrowingHistory
        // borrowed : BookCopy
        throw new UnsupportedOperationException("你必须自己实现executeActionCustomerBorrowBook方法。");
    }
    // save affected objects in the "customerBorrowBook"
    // 重载办法：重载该函数，先把需要自定义保存行为的对象，使用 touchedObjects.remove("角色名字") 移除，
    // 然后调用super.saveCustomerBorrowBookTouchedObjects，以及自己写的保存代码。
    protected void saveCustomerBorrowBookTouchedObjects(ShuxiangUserContext userContext,Customer currentCustomer ,BookCopy currentBookCopy, SmartList<ActionToken> usedTokens, Map<String, BaseEntity> touchedObjects)throws Exception{
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
            userContext.getDAOGroup().getBorrowingHistoryDAO().save((BorrowingHistory)object, BorrowingHistoryTokens.empty());
        }
        object = touchedObjects.get("borrowed");
        if (object != null){
            userContext.getDAOGroup().getBookCopyDAO().save((BookCopy)object, BookCopyTokens.empty());
        }
    }
    // send notifications of action CustomerBorrowBook
    protected void sendCustomerBorrowBookNotifications(ShuxiangUserContext userContext,Customer currentCustomer, BookCopy currentBookCopy, SmartList<ActionToken> usedTokens, Map<String, BaseEntity>touchedObjects)throws Exception{
        // by default, no any notification was sent.
    }
    // construct the result view-model of the action customerBorrowBook
    protected Object constructCustomerBorrowBookResult(ShuxiangUserContext userContext,Customer currentCustomer ,BookCopy currentBookCopy, Map<String, BaseEntity>touchedObjects)throws Exception{
        // TODO 你必须自己构建action的结果视图对象，touchedObjects中的【名字：类型】对应如下：
        // record : BorrowingHistory
        // borrowed : BookCopy
        throw new UnsupportedOperationException("你必须自己实现constructCustomerBorrowBookResult方法。");
    }
    // //////////////// Code done for action customerBorrowBook ////////////////

    // //////////////////// below are methods for action customerReturnBook ///////////////////
    // Entry point for actino customerReturnBook
    public Object actionCustomerReturnBook(ShuxiangUserContext userContext,String customerId ,String bookCopyId ,String storeId ,String employeeId) throws Exception{
        Customer currentCustomer = userContext.getDAOGroup().getCustomerDAO().load(customerId, CustomerTokens.withoutLists());
        BookCopy currentBookCopy = userContext.getDAOGroup().getBookCopyDAO().load(bookCopyId, BookCopyTokens.withoutLists());
        Store currentStore = userContext.getDAOGroup().getStoreDAO().load(storeId, StoreTokens.withoutLists());
        Employee currentEmployee = userContext.getDAOGroup().getEmployeeDAO().load(employeeId, EmployeeTokens.withoutLists());

        SmartList<ActionToken> usedTokens = checkActionCustomerReturnBook(userContext,currentCustomer ,currentBookCopy ,currentStore ,currentEmployee);

        Map<String, BaseEntity> touchedObjects = executeActionCustomerReturnBook(userContext,currentCustomer ,currentBookCopy ,currentStore ,currentEmployee, usedTokens);
        saveCustomerReturnBookTouchedObjects(userContext,currentCustomer ,currentBookCopy ,currentStore ,currentEmployee, usedTokens, touchedObjects);
        sendCustomerReturnBookNotifications(userContext,currentCustomer, currentBookCopy, currentStore, currentEmployee,usedTokens, touchedObjects);
        return constructCustomerReturnBookResult(userContext,currentCustomer ,currentBookCopy ,currentStore ,currentEmployee, touchedObjects);
    }
    // check conditions for action customerReturnBook
    protected SmartList<ActionToken> checkActionCustomerReturnBook(ShuxiangUserContext userContext, Customer currentCustomer, BookCopy currentBookCopy, Store currentStore, Employee currentEmployee) throws Exception{
        // check input actors are valid
        if (currentCustomer == null){
            throw new BookCopyManagerException("未指定有效的用户");
        }
        if (currentBookCopy == null){
            throw new BookCopyManagerException("未指定有效的借阅图书");
        }
        if (currentStore == null){
            throw new BookCopyManagerException("未指定有效的门店");
        }
        if (currentEmployee == null){
            throw new BookCopyManagerException("未指定有效的门店员工");
        }

        SmartList<ActionToken> neededTokens = new SmartList<ActionToken>();
        SmartList<ActionToken> tokens = null;
        tokens = userContext.getManagerGroup().getActionTokenManager().findTokensByActor(userContext, currentCustomer, "BORROW_BOOK");
        if (filterValidToken(tokens)){
            neededTokens.addAll(tokens);
        }
        return neededTokens;
    }
    // The actual execution of the action customerReturnBook
    protected Map<String, BaseEntity> executeActionCustomerReturnBook(ShuxiangUserContext userContext,Customer currentCustomer ,BookCopy currentBookCopy ,Store currentStore ,Employee currentEmployee, SmartList<ActionToken> usedTokens)throws Exception{
        // TODO 你必须自己实现action的过程，输出结果注意必须符合以下【名字：类型】
        // record : BorrowingHistory
        // expireBill : BorrowingExpiredSku
        // returned : BookCopy
        // order : MainOrder
        // transferRecord : BookCopyTransfer
        throw new UnsupportedOperationException("你必须自己实现executeActionCustomerReturnBook方法。");
    }
    // save affected objects in the "customerReturnBook"
    // 重载办法：重载该函数，先把需要自定义保存行为的对象，使用 touchedObjects.remove("角色名字") 移除，
    // 然后调用super.saveCustomerReturnBookTouchedObjects，以及自己写的保存代码。
    protected void saveCustomerReturnBookTouchedObjects(ShuxiangUserContext userContext,Customer currentCustomer ,BookCopy currentBookCopy ,Store currentStore ,Employee currentEmployee, SmartList<ActionToken> usedTokens, Map<String, BaseEntity> touchedObjects)throws Exception{
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
            userContext.getDAOGroup().getBorrowingHistoryDAO().save((BorrowingHistory)object, BorrowingHistoryTokens.empty());
        }
        object = touchedObjects.get("expireBill");
        if (object != null){
            userContext.getDAOGroup().getBorrowingExpiredSkuDAO().save((BorrowingExpiredSku)object, BorrowingExpiredSkuTokens.empty());
        }
        object = touchedObjects.get("returned");
        if (object != null){
            userContext.getDAOGroup().getBookCopyDAO().save((BookCopy)object, BookCopyTokens.empty());
        }
        object = touchedObjects.get("order");
        if (object != null){
            userContext.getDAOGroup().getMainOrderDAO().save((MainOrder)object, MainOrderTokens.start().withMainOrderPaymentList().withLineItemList().done());
        }
        object = touchedObjects.get("transferRecord");
        if (object != null){
            userContext.getDAOGroup().getBookCopyTransferDAO().save((BookCopyTransfer)object, BookCopyTransferTokens.empty());
        }
    }
    // send notifications of action CustomerReturnBook
    protected void sendCustomerReturnBookNotifications(ShuxiangUserContext userContext,Customer currentCustomer, BookCopy currentBookCopy, Store currentStore, Employee currentEmployee, SmartList<ActionToken> usedTokens, Map<String, BaseEntity>touchedObjects)throws Exception{
        // by default, no any notification was sent.
    }
    // construct the result view-model of the action customerReturnBook
    protected Object constructCustomerReturnBookResult(ShuxiangUserContext userContext,Customer currentCustomer ,BookCopy currentBookCopy ,Store currentStore ,Employee currentEmployee, Map<String, BaseEntity>touchedObjects)throws Exception{
        // TODO 你必须自己构建action的结果视图对象，touchedObjects中的【名字：类型】对应如下：
        // record : BorrowingHistory
        // expireBill : BorrowingExpiredSku
        // returned : BookCopy
        // order : MainOrder
        // transferRecord : BookCopyTransfer
        throw new UnsupportedOperationException("你必须自己实现constructCustomerReturnBookResult方法。");
    }
    // //////////////// Code done for action customerReturnBook ////////////////

