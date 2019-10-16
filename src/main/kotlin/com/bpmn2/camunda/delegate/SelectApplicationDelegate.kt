package com.bpmn2.camunda.delegate

import com.bpmn2.camunda.model.application
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService
import org.springframework.dao.support.DataAccessUtils.singleResult
import org.camunda.bpm.engine.runtime.EventSubscription
import org.camunda.bpm.engine.runtime.ProcessInstance
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Component("selectApplicationDelegate")
class SelectApplicationDelegate : JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        var rs = execution.processEngineServices.runtimeService
        var processInstancesList = rs.createProcessInstanceQuery().active().processDefinitionKey("ApplicationApprove").list()
        var applications = ArrayList<application>()
        processInstancesList.forEach() {
            var amountFromProcess = rs.createVariableInstanceQuery().activityInstanceIdIn(it.id).variableName("Amount").singleResult().value as Long
            var idFromProcess = rs.createVariableInstanceQuery().activityInstanceIdIn(it.id).variableName("ID").singleResult().value as Long
            var descriptionFromProcess = rs.createVariableInstanceQuery().activityInstanceIdIn(it.id).variableName("Description").singleResult().value as String
            var processId = it.id


            var application = application().apply {
                id = idFromProcess
                desctiption = descriptionFromProcess
                amount = amountFromProcess
                processInstanceId = processId
            }
            applications.add(application)

        }
        execution.setVariable("applications", applications)

//        val pi = rs.createProcessInstanceQuery().active().processDefinitionKey("ApplicationApprove").singleResult();
//
//        val subscription = rs.createEventSubscriptionQuery()
//                .processInstanceId(pi.id).eventType("message").singleResult()
//        var mes = subscription.eventName
//
//        val nameTable = mutableMapOf<String, Any>()
//        nameTable.put("acceptanceResult",false)
//        rs.messageEventReceived("WaitAcceptance", subscription.executionId, nameTable)
    }
}