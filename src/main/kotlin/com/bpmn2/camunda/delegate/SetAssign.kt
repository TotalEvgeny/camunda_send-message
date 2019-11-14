package com.bpmn2.camunda.delegate

import com.bpmn2.camunda.service.camunda.RequestService
import org.camunda.bpm.engine.ProcessEngineServices
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.DelegateTask
import org.camunda.bpm.engine.delegate.TaskListener
import org.camunda.bpm.engine.identity.GroupQuery
import org.camunda.bpm.engine.runtime.Execution
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.camunda.bpm.engine.impl.context.Context;


@Component("SetAssign")
class SetAssign : TaskListener {
//    @Autowired
//    private val runtimeService: RuntimeService? = null

    @Autowired
    private val execution: Execution? = null

    private val processEngineServices: ProcessEngineServices? = null

    fun execute(execution: DelegateExecution) {
//        execution.s
//        var assignGroup = execution.processEngineServices.identityService.createGroupQuery().groupId("camunda-admin")
//        var rs = execution.processEngineServices.runtimeService
//        rs.set
    }

    @Autowired
    private val requestService: RequestService? = null

    override fun notify(delegateTask: DelegateTask) {
        var assignGroup = delegateTask.execution.processEngineServices.identityService.createGroupQuery() as GroupQuery
        delegateTask.addCandidateGroup("support")

        val identityService = Context.getProcessEngineConfiguration().getIdentityService()
        val user = identityService.createUserQuery().userId("demo").singleResult()

        delegateTask.setVariableLocal("name", "123") //only in this task

        //data from DB
        var listRequest = requestService?.findAll();
        var nameFirstRequest = listRequest?.first()?.name
        var idRequest = listRequest?.first()?.id
        delegateTask.setVariable("Description", nameFirstRequest)
        delegateTask.setVariable("Comment", idRequest)
    }

}