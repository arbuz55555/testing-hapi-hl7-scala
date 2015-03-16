package validation

import ca.uhn.hl7v2.parser.PipeParser
import ca.uhn.hl7v2.{HapiContext, DefaultHapiContext, HL7Exception}
import ca.uhn.hl7v2.validation.impl.ValidationContextFactory
import ca.uhn.hl7v2.validation.ValidationContext
import org.scalatest.FunSuite
/**
* Copyright 2011-2014 Lukasz Sztygiel 5x5 Solutions LTD (www.5x5sols.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIESMessageValidationSpec OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
class MessageValidationSuite extends FunSuite {

  test("Hapi parser should produce HL7Exception for invalid HL7Message") {

    val invMsg = "MSH|^~\\&|MedSeries|CAISI_1-2|PLS|3910|200903230934||ADT^A31^ADT_A05|75535037-1237815294895|P^T|2.4\r" +
      "EVN|0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789|200903230934\r" +
      "PID|1||29^^CAISI_1-2^PI~\"\"||Test300^Leticia^^^^^L||19770202|M||||||||||||||||||||||"
    val context: HapiContext = new DefaultHapiContext()
    val validationContext: ValidationContext = ValidationContextFactory.defaultValidation()
    context.setValidationContext(validationContext)
    val parser: PipeParser = context.getPipeParser()

    intercept[HL7Exception] {
      parser.parse(invMsg)
    }
  }
}


