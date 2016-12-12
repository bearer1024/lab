using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace awtCw2Q3
{
    public class PayForBooking
    {
        public static Boolean pay(Int32 studentNo,Int32 studentCardNo)
        {
            //simple logic.......
            if (studentNo > 0 && studentCardNo > 0)
            {
                Console.WriteLine("this card " + studentCardNo + "has been charged succeffully");
                return true;
            }
            else return false;
        }
    }
}