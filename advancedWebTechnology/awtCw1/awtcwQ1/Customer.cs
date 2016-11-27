using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace awtcwQ1
{
     public abstract class Customer
    {
        private string customerName;
        private int customerNumber;
        private string customerType;
        private int rentDays;

        

        public string CustomerName
        {
            get
            {
                return customerName;
            }

            set
            {
                customerName = value;
            }
        }

        public string CustomerType
        {
            get
            {
                return customerType;
            }

            set
            {
                customerType = value;
            }
        }

        public int RentDays
        {
            get
            {
                return rentDays;
            }

            set
            {
                rentDays = value;
            }
        }

        public int CustomerNumber
        {
            get
            {
                return customerNumber;
            }

            set
            {
                customerNumber = value;
            }
        }

        public abstract void bookCar(int payOpertion, int days);


        public abstract bool payBill(int payOpertionNo);
       

    }
}
