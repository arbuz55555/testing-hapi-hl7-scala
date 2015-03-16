package parser.v23

import ca.uhn.hl7v2.model.Message
import ca.uhn.hl7v2.model.v23.datatype.XPN
import ca.uhn.hl7v2.model.v23.message.ADT_A01
import ca.uhn.hl7v2.parser.EncodingNotSupportedException
import ca.uhn.hl7v2.{DefaultHapiContext, HL7Exception}
import org.scalatest.FunSuite

class Adt1Suite extends FunSuite {

  test("ADT message V22 has ADT type A01 and Patient with familyName 'SMITH") {

    val msg = "MSH|^~\\&|HIS|RIH|EKG|EKG|199904140038||ADT^A01||P|2.3\r" +
      "PID|0001|00009874|00001122|A00977|SMITH^JOHN^M|MOM|19581119|F|NOTREAL^LINDA^M|C|564 SPRING ST^^NEEDHAM^MA^02494^US|0002|(818)565-1551|(425)828-3344|E|S|C|0000444444|252-00-4414||||SA|||SA||||NONE|V1|0001|I|D.ER^50A^M110^01|ER|P00055|11B^M011^02|070615^BATMAN^GEORGE^L|555888^NOTREAL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^NOTREAL^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|199904101200||||5555112333|||666097^NOTREAL^MANNY^P\r" +
      "PV1|0001|I|D.ER^1F^M950^01|ER|P000998|11B^M011^02|070615^BATMAN^GEORGE^L|555888^OKNEL^BOB^K^DR^MD|777889^NOTREAL^SAM^T^DR^MD^PHD|ER|D.WT^1A^M010^01|||ER|AMB|02|070615^VOICE^BILL^L|ER|000001916994|D||||||||||||||||GDD|WA|NORM|02|O|02|E.IN^02D^M090^01|E.IN^01D^M080^01|199904072124|199904101200|||||5555112333|||666097^DNOTREAL^MANNY^P\r" +
      "PV2|||0112^TESTING|55555^PATIENT IS NORMAL|NONE|||19990225|19990226|1|1|TESTING|555888^NOTREAL^BOB^K^DR^MD||||||||||PROD^003^099|02|ER||NONE|19990225|19990223|19990316|NONE\r" +
      "AL1||SEV|001^POLLEN\r" +
      "GT1||0222PL|NOTREAL^BOB^B||STREET^OTHER STREET^CITY^ST^77787|(444)999-3333|(222)777-5555||||MO|111-33-5555||||NOTREAL GILL N|STREET^OTHER STREET^CITY^ST^99999|(111)222-3333\r" +
      "IN1||022254P|4558PD|BLUE CROSS|STREET^OTHER STREET^CITY^ST^00990||(333)333-6666||221K|LENIX|||19980515|19990515|||PATIENT01 TEST D||||||||||||||||||02LL|022LP554"

    val hapiContext = new DefaultHapiContext()
    val p = hapiContext.getGenericParser()

    var hpiMsg:Message = null

    try {
      hpiMsg = p.parse(msg)
    } catch {
      case e:EncodingNotSupportedException => e.printStackTrace()
      case e1:HL7Exception => e1.printStackTrace()
    }

    val adtMsg = hpiMsg.asInstanceOf[ADT_A01]
    val msh = adtMsg.getMSH

    val msgTrigger = msh.getMessageType.getTriggerEvent.getValue
    val msgType = msh.getMessageType.getMessageType.getValue

    assert(msgType == "ADT")
    assert(msgTrigger == "A01")

    val patientNames:Array[XPN] = adtMsg.getPID.getPatientName()
    val familyName = patientNames(0).getFamilyName()
    assert(familyName.toString == "SMITH")
  }
}