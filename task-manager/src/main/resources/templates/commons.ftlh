<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Список дел</title>
        <link rel="stylesheet" href="/css/style.css" type="text/css"/>
        <link rel="stylesheet" href="/css/taskLists.css" type="text/css"/>
        <link rel="stylesheet" href="/css/tasks.css" type="text/css"/>
        <link rel="stylesheet" href="/css/error.css" type="text/css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@200&display=swap" rel="stylesheet">
    </head>
    <body>
    <div class="content">
        <#if user??>
            <form class="form logout" action="/logout" method="get">
                <@input>
                    <input type="submit" value="Выход">
                </@input>
            </form>
        </#if>
        <#nested>
    </div>
    </body>
    </html>
</#macro>

<#macro textBox>
    <div class="columnBox">
        <#nested>
    </div>
</#macro>

<#macro deleteButton requestUrl itemId>
    <form class="deleteButton" action="${requestUrl}" method="post">
        <label>
            <input hidden type="text" name="id" value=${itemId}>
        </label>
        <label>
            <input type="submit" value="&#10060;">
        </label>
    </form>
</#macro>

<#macro input>
    <@textBox>
        <label>
            <#nested>
        </label>
    </@textBox>
</#macro>