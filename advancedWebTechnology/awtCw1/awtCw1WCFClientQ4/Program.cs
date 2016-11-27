using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using awtCw1WCFClientQ4.UpperOperatorService;
using awtCw1WCFClientQ4.CustomerServiceReference;

namespace awtCw1WCFClientQ4
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("---------------------------------------Welcome to car renting system--------------------------------------");
            Console.WriteLine("a) search for a customer \t b)enter new customer \t c) show how many customers exist \t e)enter e to exit");
            Console.WriteLine("please enter a   b   c   to use,     e   to quit");
            string userInput;
            userInput = Console.ReadLine();
            SCustomerServiceClient client = new SCustomerServiceClient();
            
            while (userInput != "e")
            {
                
                switch (userInput)
                {
                    case "a":
                        Console.WriteLine("please input name to search , it will atomaticlly convert to uppercase to search");
                        string userInputName = Console.ReadLine();
                        try
                        {
                            UpperOperatorPortTypeClient upperStringClient = new UpperOperatorPortTypeClient();
                            string upperName = upperStringClient.upper(userInputName);
                            Console.WriteLine("you are search for:  " + upperName);
                            Customer customer = new Customer();
                            customer = client.getCustomerByName(upperName);
                            //To check this userName exists or not
                            if(customer.CustomerId != 0)
                            {
                                Console.WriteLine("customer name:" + customer.CustomerName + "\t" + "customer id:" + customer.CustomerId + "\t" + "card number:" + customer.CustomerId);
                            }
                            else
                            {
                                Console.WriteLine("there is no this customer, u can create one");
                            }
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.ToString());
                        }
                        break;

                    case "b":
                        Console.WriteLine("you are about to add a customer");
                        Console.WriteLine("please input name for  this new customer, it will atomaticlly convert to uppercase ");
                        string userInputNameForAdding = Console.ReadLine();
                        UpperOperatorPortTypeClient upperStringClientForAdding = new UpperOperatorPortTypeClient();
                        string upperNameForAdding = upperStringClientForAdding.upper(userInputNameForAdding);
                        Console.WriteLine("please input customerId for  this new customer ");
                        int customerId = Convert.ToInt32(Console.ReadLine());
                        Console.WriteLine("please input card number for  this new customer ");
                        int customerCardNumber = Convert.ToInt32(Console.ReadLine());
                        bool success = client.addNewCustomer(customerId, upperNameForAdding, customerCardNumber);
                        if (success)
                        {
                            Console.WriteLine("customer has been added successfully");
                        }else
                        {
                            Console.WriteLine("failed to add this customer");
                        }
                        break;

                    case "c":
                        int countForCustomer = client.countCustomerNumber();
                        Console.WriteLine("there are      " + countForCustomer + "    in the database");
                        Console.WriteLine("do u want to see who they are? if yes , enter y");
                        string wantToSee = Console.ReadLine();
                        if(wantToSee == "y")
                        {
                            Customer[] customers = client.getAllCustomers();
                            for (int i = 0; i < customers.Length; i++)
                            {
                                Console.WriteLine(customers[i].CustomerName);
                            }
                        }
                        break;

                }

                Console.WriteLine("---------------------------------------Welcome to car renting system--------------------------------------");
                Console.WriteLine("a) search for a customer \t b)enter new customer \t c) show how many customers exist e)enter e to exit");
                Console.WriteLine("please enter a   b   c   to use,     e   to quit");
                userInput = Console.ReadLine();
            }
        }
    }
}
