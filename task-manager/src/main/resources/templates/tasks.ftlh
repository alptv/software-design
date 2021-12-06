<#-- @ftlvariable name="tasks" type="task.manager.model.Task[]" -->
<#import "commons.ftlh" as commons>

<@commons.page>
    <h1 class="header">${taskList.getName()}</h1>
    <form class="form addTask" action="/task/addTask" method="post">
        <input name="taskListId" type="text" value="${taskList.getId()}" hidden/>
        <@commons.input>
            <input name="name" type="text" placeholder="Введите название задачи"/>
        </@commons.input>
        <@commons.input>
            <textarea name="description" placeholder="Введите описание задачи"></textarea>
        </@commons.input>
        <@commons.input>
            <input type="submit" value="Сохранить">
        </@commons.input>
    </form>
    <div class="tasksBoard">
        <#list tasks as task>
            <#assign status="${task.getDone()?then('done', 'notDone')}">
            <div class="taskTicket ${status}">
                <@commons.textBox>
                    <form action="/task/changeTaskStatus" method="post">
                        <label>
                            <input type="text" name="id" hidden value="${task.getId()}"/>
                        </label>
                        <h3>
                            <button type="submit">
                                ${task.getName()}
                            </button>
                        </h3>
                    </form>
                    <p>${task.getDescription()}</p>
                </@commons.textBox>
            </div>
        </#list>
    </div>
</@commons.page>
