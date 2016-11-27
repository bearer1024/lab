using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace awtcwQ1
{
    public class CommercialCustomer : Customer
    {
        string[] payOpertionArray = new string[] { "Cash", "Card" };

        public CommercialCustomer(String name,int number)
        {
            this.CustomerName = name;
            this.CustomerNumber = number;
        }

        public override bool payBill(int payOpertionNo)
        {
                if (payOpertionNo == 0 || payOpertionNo == 1)
                {
                    Console.WriteLine("you paid the bill by" + payOpertionArray[payOpertionNo]);
                    return true;
                }
                return false;
        }
        public override void bookCar(int payOpertion,int days)
        {
            Console.WriteLine("you are private customer");
            this.CustomerType = "commercialCustomer";
            Console.WriteLine("you booked car succeffully");
            this.RentDays = days;
            sendMonthlyBill();
            payBill(payOpertion);
            produceInvoice(days);
        }

        public void sendMonthlyBill()
        {
            Console.WriteLine("we have sent bill to your account");

        }

        public void produceInvoice(int days)
        {
            Console.WriteLine("your rent fee is:  "+100*days+" £");
            Console.WriteLine("___________________________________________________________________________");


        }
    }
}
