<#import "macro/main.ftl" as c>
<@c.mainPage>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <#if error??>
                    <div class="alert alert-danger">
                        The email or password you have entered is invalid, try again.
                    </div>
                </#if>
                <form action="login" method='POST'>
                    <div class="form-group">
                        <label for="usernameId">UserName</label>
                        <input type="text" name="username" class="form-control" id="usernameId" aria-describedby="emailHelp"
                               placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label for="passwordId">Password</label>
                        <input type="password" name="password" class="form-control" id="passwordId" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</@c.mainPage>