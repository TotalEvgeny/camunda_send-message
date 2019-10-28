//package com.bpmn2.camunda
//
//import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
//import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.runApplication
//import org.camunda.bpm.engine.RuntimeService
//import org.springframework.context.event.EventListener
//
//
////@EnableProcessApplication
////@SpringBootApplication
//class CamundaApplication
//
//fun main(args: Array<String>) {
//	runApplication<CamundaApplication>(*args)
//
//}
//
//@Autowired
//val runtimeService: RuntimeService? = null
//
//@EventListener
//private fun processPostDeploy(event: PostDeployEvent) {
//	runtimeService?.startProcessInstanceByKey("loanApproval")
//}
//
////var configuration: MailConfiguration? = null
////
////var notificationService: MailNotificationService? = null
////
////@PreUndeploy
////fun onPreUndeploy(processEngine: ProcessEngine) {
////	notificationService!!.stop()
////
////	val mailService = MailServiceFactory.getService(configuration)
////	mailService.close()
//////	eventPublisher.publishEvent(PreUndeployEvent(processEngine))
////}
////
////@PostDeploy
////fun onPostDeploy(processEngine: ProcessEngine) {
////	val runtimeService = processEngine.runtimeService
////
////	configuration = MailConfigurationFactory.getConfiguration()
////	notificationService = MailNotificationService(configuration)
////
////	notificationService!!.registerMailHandler { mail ->
////		runtimeService.startProcessInstanceByKey("printProcess",
////				Variables.createVariables()
////						.putValue("mail", mail)
////						.putValue("invoice", getInvoicePath()))
////	}
////
////	notificationService!!.start()
//////	eventPublisher.publishEvent(PostDeployEvent(processEngine))
////}
////
////fun getInvoicePath(): String {
////	val resource = String::class.java.getResource("/invoice.pdf")
////			?: throw IllegalStateException("Cannot found invoice file: invoice.pdf")
////
////	try {
////		val file = File(resource.toURI())
////		return file.getPath()
////
////	} catch (e: URISyntaxException) {
////		throw RuntimeException(e)
////	}
////
////}