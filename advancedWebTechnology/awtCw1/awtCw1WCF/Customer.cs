using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading.Tasks;

namespace awtCw1WCF
{
    public class Customer
    {

            private int customerId,customerCardNo;
            private string customerName;

        public int CustomerCardNo
        {
            get
            {
                return customerCardNo;
            }

            set
            {
                customerCardNo = value;
            }
        }

        public int CustomerId
        {
            get
            {
                return customerId;
            }

            set
            {
                customerId = value;
            }
        }

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

        public override string ToString()
            {

            return "customer id :" + this.customerId + "\t customer name:" + this.customerName + "\t customer card number:" + this.customerCardNo;
            }
        }
}

