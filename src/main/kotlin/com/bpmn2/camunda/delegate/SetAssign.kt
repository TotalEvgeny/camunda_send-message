package com.bpmn2.camunda.delegate

import com.bpmn2.camunda.model.application
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.ProcessEngineServices
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.DelegateTask
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.delegate.TaskListener
import org.camunda.bpm.engine.identity.GroupQuery
import org.camunda.bpm.engine.runtime.Execution
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import org.springframework.dao.support.DataAccessUtils.singleResult
import org.camunda.bpm.engine.impl.context.Context.getProcessEngineConfiguration
import org.camunda.bpm.engine.IdentityService
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

    override fun notify(delegateTask: DelegateTask) {
        var assignGroup = delegateTask.execution.processEngineServices.identityService.createGroupQuery() as GroupQuery
        delegateTask.addCandidateGroup("support")

        val identityService = Context.getProcessEngineConfiguration().getIdentityService()
        val user = identityService.createUserQuery().userId("demo").singleResult()
    }

}