<#macro mainPage>
    <!DOCTYPE html>
    <#import "/spring.ftl" as spring />
    <html lang="en">
    <head>
        <meta charset="utf-8"/>
        <title>Unit-Structure-Helper</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <#include "sources.ftl">
    </head>
    <body>
    <#include "header.ftl">
    <main role="main" class="container">
        <#nested>
    </main>
    </body>
    </html>
</#macro>