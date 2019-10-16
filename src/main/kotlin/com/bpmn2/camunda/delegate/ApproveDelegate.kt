package com.bpmn2.camunda.delegate

import com.bpmn2.camunda.model.application
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component
import java.util.*

@Component ("approveDelegate")
class ApproveDelegate:JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        var applications  = execution.getVariable("applications") as List<application>
        applications.forEach(){
            it.comment = "SomeComment"
            it.acceptanceResult = Random().nextBoolean()
        }

        execution.setVariable("applications", applications)
    }
}