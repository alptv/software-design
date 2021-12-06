<#import "commons.ftlh" as commons>


<@commons.page>
    <form class="form" method="post">
        <@commons.input>
            <input type="text" name="login" placeholder="Введите логин">
        </@commons.input>
        <@commons.input>
            <input type="text" name="password" placeholder="Введите пароль">
        </@commons.input>
        <@commons.input>
            <input formaction="/enter" type="submit" value="Войти">
        </@commons.input>
        <@commons.input>
            <input formaction="/register" type="submit" value="Зарегистрироваться">
        </@commons.input>
    </form>
</@commons.page>