<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"/>
                </svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/index" class="nav-link px-2 text-secondary">Home</a></li>
                <li><a href="/info" class="nav-link px-2 text-secondary">Info</a></li>
                <li><a href="/blocked-users" class="nav-link px-2 text-white">Blocked</a></li>
            </ul>

            <div class="text-end">
                <#--                <@security.authorize access="!isAuthenticated()">-->
                <a class="btn btn-outline-light me-2" href="/login" role="button">Login</a>
                <#--                </@security.authorize>-->
                <#--                <@security.authorize access="isAuthenticated()">-->
                <a class="btn btn-warning" href="/logout" role="button">LogOut</a>
                <#--                </@security.authorize>-->

            </div>
        </div>
    </div>
</header>