using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace awtcwQ1
{
    class Program
    {
        static void Main(string[] args)
        { 
            Console.WriteLine("-----------Welcome to car renting system---------");
            Console.WriteLine("type 1 for private customer \t type 2 for commercial customer \t enter -1 to exit");
            int userInput;
            userInput = Convert.ToInt32(Console.ReadLine());
            while (userInput != -1)
            {
                //Console.WriteLine("type 1 for private customer \t type 2 for commercial customer");
                //int userInput = Convert.ToInt32(Console.ReadLine());
                Console.WriteLine("pls input customerName");
                string customerName = Console.ReadLine();
                Console.WriteLine("pls input customerNumber");
                int customerNumber = Convert.ToInt32(Console.ReadLine());
                Console.WriteLine("how many days do you need?");
                int days = Convert.ToInt32(Console.ReadLine());
                switch (userInput)
                {
                    case 1:

                        PrivateCusomer privateCustomer = new PrivateCusomer(customerName, customerNumber);
                        Console.WriteLine("type 1 for paying by cash \t type 2 for paying by card");
                        int payOpertionNo = Convert.ToInt32(Console.ReadLine());
                        privateCustomer.bookCar(payOpertionNo,days);
                        break;
                    case 2:
                        CommercialCustomer commercialCustomer = new CommercialCustomer(customerName, customerNumber);
                        Console.WriteLine("type 1 for paying by cash \t type 2 for paying by card");
                        int payOpertionNoForCommercial = Convert.ToInt32(Console.ReadLine());
                        commercialCustomer.bookCar(payOpertionNoForCommercial, days);
                        break;
                    }
                Console.WriteLine("type 1 for private customer \t type 2 for commercial customer \t enter -1 to exit");
                userInput = Convert.ToInt32(Console.ReadLine());
            }
            }
     }
}

