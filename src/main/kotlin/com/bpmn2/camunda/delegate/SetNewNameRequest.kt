package com.bpmn2.camunda.delegate

import com.bpmn2.camunda.service.camunda.RequestService
import org.camunda.bpm.engine.delegate.DelegateTask
import org.camunda.bpm.engine.delegate.TaskListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component("SetNewNameRequest")
class SetNewNameRequest : TaskListener {
    @Autowired
    private val requestService: RequestService? = null

    override fun notify(delegateTask: DelegateTask) {
        var description = delegateTask.getVariable("Description") as String
        var idRequest = delegateTask.getVariable("Comment") as UUID
        var request = requestService?.findById(idRequest)
        request?.name = description
        requestService?.createRequest(request)
    }
}