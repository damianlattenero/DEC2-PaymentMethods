package mvctest.web

import java.lang.Long
import java.util
import javax.validation.Valid

import mvctest.domain.PaymentMethods
import mvctest.service.PaymentMethodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation._

@Controller
@RequestMapping(Array("/paymentmethods"))
class PaymentMethodController @Autowired()(private val paymentMethodRepository: PaymentMethodRepository) {

  @GetMapping(Array("/{idSite}", MediaType.APPLICATION_JSON_VALUE))
  def getPaymentMethods(@PathVariable("idSite") idSite: String): ResponseEntity[util.ArrayList[PaymentMethods]] = {
    var p = new PaymentMethods()
    p.setAddress("f1")
    p.setId(1l)
    p.setName("fr")
    p.setZip(idSite)
    val list: util.ArrayList[PaymentMethods] = new util.ArrayList[PaymentMethods]()
    list.add(p)
    list.add(p)
    new ResponseEntity[util.ArrayList[PaymentMethods]](list,HttpStatus.OK)
  }




  @GetMapping
  def list(model: Model) = {
    val paymentmethods = paymentMethodRepository.findAll()
    model.addAttribute("paymentmethods", paymentmethods)
    "paymentmethods/list"
  }

  @GetMapping(Array("/edit/{id}"))
  def edit(@PathVariable("id") id: Long, model: Model) = {
    model.addAttribute("paymentMethod", paymentMethodRepository.findOne(id))
    "paymentmethods/edit"
  }

  @GetMapping(params = Array("form"))
  def createForm(model: Model) = {
    model.addAttribute("paymentMethod", new PaymentMethods())
    "paymentmethods/create"
  }

  @PostMapping
  def create(@Valid paymentMethod: PaymentMethods, bindingResult: BindingResult) =
    if (bindingResult.hasErrors()) {
      "paymentmethods/create"
    } else {
      paymentMethodRepository.save(paymentMethod)
      "redirect:/paymentmethods"
    }


  @PostMapping(value = Array("/update"))
  def update(@Valid paymentMethod: PaymentMethods, bindingResult: BindingResult) =
    if (bindingResult.hasErrors()) {
      "paymentmethods/edit"
    } else {
      paymentMethodRepository.save(paymentMethod)
      "redirect:/paymentmethods"
    }


  @GetMapping(value = Array("/delete/{id}"))
  def delete(@PathVariable("id") id: Long) = {
    paymentMethodRepository.delete(id)
    "redirect:/paymentmethods"
  }

}