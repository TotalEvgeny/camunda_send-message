package com.bpmn2.camunda.delegate

import com.bpmn2.camunda.model.application
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component("sendAllDelegate")
class SendAllDelegate : JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        var rs = execution.processEngineServices.runtimeService
        var applications = execution.getVariable("applications") as List<application>


        applications.forEach() {
            //my code
            var subscription = rs.createEventSubscriptionQuery()
                    .processInstanceId(it.processInstanceId).eventType("message").eventName("WaitAcceptance").singleResult()
            if (subscription != null) {
                var myVariables = mutableMapOf<String, Any>()
                myVariables.put("acceptanceResult", it.acceptanceResult)
                myVariables.put("Comment", it.comment as String)

                rs.messageEventReceived("WaitAcceptance", subscription.executionId, myVariables)
// была ошибка при отправке в процессы, которые уже не ждут сообщение
//                rs.createMessageCorrelation("WaitAcceptance")
//                        .processInstanceId(it.processInstanceId)
//                        .setVariable("acceptanceResult", it.acceptanceResult)
//                        .setVariable("Comment", it.comment)
//                        .correlate()
            }
        }

    }
}