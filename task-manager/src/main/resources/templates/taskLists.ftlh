<#-- @ftlvariable name="taskLists" type="task.manager.model.TaskList[]" -->
<#import "commons.ftlh" as commons>

<@commons.page>
    <h1 class="header">Списки задач</h1>
    <form class="form" action="/taskList/createTaskList" method="post">
        <@commons.textBox>
            <label>
                <input name="name" type="text" placeholder="Введите название для следующего списка задач"/>
            </label>
        </@commons.textBox>
    </form>
    <div class="taskLists">
        <ul>
            <#list taskLists as taskList>
                <li>
                    <@commons.textBox>
                        <a href="/taskList/${taskList.getId()}">
                            ${taskList.getName()}
                        </a>
                    </@commons.textBox>
                    <@commons.deleteButton requestUrl="/taskList/deleteTaskList" itemId='${taskList.getId()}'/>
                </li>
            </#list>
        </ul>
    </div>
</@commons.page>
