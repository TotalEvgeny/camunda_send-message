# camunda_send-message

![alt text](http://srv-gitlab-01.e-lab.icl.kazan.ru/Evgeny.Voloczkoy/camunda/blob/master/src/main/resources/bpmn/acceptanceApplication.png)

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