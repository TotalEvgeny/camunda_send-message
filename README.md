# camunda_send-message

![Alt text](/src/main/resources/bpmn/acceptanceApplication.png)
![Alt text](src/main/resources/bpmn/ApproveAcc.png)

UseFull
* Spring BOOT  
*  Maven

Example:
sending messages from a process to another process that is waiting for it

 `rs.messageEventReceived("WaitAcceptance", subscription.executionId, myVariables)`
 
 Another code with strong process, who waiting
 
 `
rs.createMessageCorrelation("WaitAcceptance")
.processInstanceId(it.processInstanceId)
.setVariable("acceptanceResult", it.acceptanceResult)
.setVariable("Comment", it.comment)
.correlate()`