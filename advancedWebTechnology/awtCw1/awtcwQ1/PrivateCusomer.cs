using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace awtcwQ1
{
    class PrivateCusomer : Customer
    {
        string[] payOpertionArray = new string[] { "Cash", "Card" };
        public override void bookCar(int payOpertion,int days)
        {
            Console.WriteLine("you are private customer");
            this.CustomerType = "privateCustomer";
            payBill(payOpertion);
            Console.WriteLine("thank you for renting car from our company");
            Console.WriteLine("___________________________________________________________________________");
        }

        public PrivateCusomer(string name,int number)
        {
            this.CustomerName = name;
            this.CustomerNumber = number;
        }
        public  override bool payBill(int payOpertionNo)
        {
            if (payOpertionNo == 0 || payOpertionNo == 1)
            {
                Console.WriteLine("you paid the bill by" + payOpertionArray[payOpertionNo]);
                return true;
            }
            return false;
        }
    }
}
