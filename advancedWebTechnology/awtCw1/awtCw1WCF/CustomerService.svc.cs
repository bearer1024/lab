using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using MySql.Data.MySqlClient;


namespace awtCw1WCF
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "CustomerService" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select CustomerService.svc or CustomerService.svc.cs at the Solution Explorer and start debugging.
    public class CustomerService : ISCustomerService
    {

        static MySqlConnection getConnection()
        {
            //this static method will return a connection constructed by connection string
            string myConnectionString = "Database=rentalsystem;DataSource = localhost; User Id = root; Password = root";
            MySqlConnection connection = new MySqlConnection(myConnectionString);
            return connection;
        }

        public Customer getCustomerByName(string name)
        {
            Customer customer = new Customer();
            try
            {
                MySqlConnection connection = getConnection();
                connection.Open();
                MySqlCommand mycm = new MySqlCommand("", connection);
                mycm.Prepare();
                mycm.CommandText = String.Format("select * from privatecustomer where customerName=?name_para");
                mycm.Parameters.AddWithValue("?name_para", name);
                MySqlDataReader msdr = mycm.ExecuteReader();

                while (msdr.Read())
                {
                    if (msdr.HasRows)
                    {

                        customer.CustomerId = msdr.GetInt32("customerId");
                        customer.CustomerCardNo = msdr.GetInt32("customerCardNo");
                        customer.CustomerName = msdr.GetString("customerName");
                        Console.WriteLine(customer);
                    }
                }
                msdr.Close();
                connection.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
            return customer;
        }

        public bool addNewCustomer(int customerId, string customerName, int customerCardNo)
        {
            try
            {
                MySqlConnection connection = getConnection();
                connection.Open();
                MySqlCommand mycm = new MySqlCommand("", connection);
                mycm.Prepare();
                mycm.CommandText = String.Format("insert into privatecustomer ( customerId, customerName, customerCardNo) VALUES (?customerId, ?customerName, ?customerCardNo)");
                mycm.Parameters.AddWithValue("?customerId", customerId);
                mycm.Parameters.AddWithValue("?customerName", customerName);
                mycm.Parameters.AddWithValue("?customerCardNo", customerCardNo);
                MySqlDataReader msdr = mycm.ExecuteReader();
                Console.WriteLine("the customer has been added successfully");


                msdr.Close();
                connection.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                return false;
            }
            return true;
        }

        public int countCustomerNumber()
        {
            List<Customer> customers = new List<Customer>();
            customers = getAllCustomers();
            int totalCustomer = customers.Count;
            return totalCustomer;
        }

        public List<Customer> getAllCustomers()
        {
            //a list of customers
            List<Customer> customers = new List<Customer>();
            try
            {
                //Open connection
                MySqlConnection connection = getConnection();
                connection.Open();
                // SQL query assignment
                MySqlCommand mycm = new MySqlCommand("select * from privatecustomer",
               connection);
                //execute query
                MySqlDataReader msdr = mycm.ExecuteReader();
                while (msdr.Read())
                {
                    if (msdr.HasRows)
                    {
                        Customer customer = new Customer();
                        //read each column and assign value to corresponding property of Customer
                        customer.CustomerId = msdr.GetInt32("customerId");
                        customer.CustomerCardNo = msdr.GetInt32("customerCardNo");
                        customer.CustomerName = msdr.GetString("customerName");
                        //add this customer to the list
                        customers.Add(customer);
                    }
                }
                msdr.Close();
                connection.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
            //return customer list
            return customers;
        }
    }
}

