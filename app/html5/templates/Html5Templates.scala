package html5.templates

import play.api.templates._

object Html5Templates {
  
  /**
   * Generates form validation attributes
   */
  def toValidation(field: play.api.data.Field) = {
    val out = Html.empty
    field.constraints.foreach(
	constraint => constraint._1 match {
        case "constraint.required" => out + Html(" required")
        case "constraint.maxLength" => out + constraintValue("maxlength", constraint._2)
        case "constraint.minLength" => out + Html(" pattern=\".{" + constraint._2.head + ",}\"")
        // not working yet see issues
        //case "constraint.pattern" => out + constraintValue("pattern", constraint._2)
        case _ => ()
      })
    out
  }
  
  private def constraintValue(label: String, values : Seq[Any]) = {
	val value = values.size match {
      	    case 0 => None
      	    case _ => Some(values.head)
        }  
	val out = value.map(" " + label + "=\"" + _ + "\"")
	Html(out.getOrElse(""))
  }
}
