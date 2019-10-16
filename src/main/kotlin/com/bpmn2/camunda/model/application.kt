package com.bpmn2.camunda.model

import org.apache.ibatis.jdbc.Null
import java.io.Serializable

class application : Serializable {
    var processInstanceId: String? = null
    var amount: Long? = null
    var desctiption: String? = null
    var comment: String? = null
    var id: Long? = null
    var acceptanceResult: Boolean = false

}