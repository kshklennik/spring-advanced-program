<#import "macro/main.ftl" as c>
<@c.mainPage>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-header">
                    <h5 class="modal-title">Blocked users</h5>
                </div>
            </div>
            <div class="modal-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">UserName</th>
                        <th scope="col">Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list blocked as bUser>
                        <tr>
                            <th scope="row">1</th>
                            <td>${bUser.user}</td>
                            <td>time</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</@c.mainPage>