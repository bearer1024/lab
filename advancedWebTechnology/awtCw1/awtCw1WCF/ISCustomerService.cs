using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace awtCw1WCF
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "ISCustomerService" in both code and config file together.
    [ServiceContract]
    public interface ISCustomerService
    {

        [OperationContract]
        Customer getCustomerByName(string name);

        [OperationContract]
        bool addNewCustomer(int customerId, string customerName, int customerCardNo);

        [OperationContract]
        int countCustomerNumber();


        [OperationContract]
        List<Customer> getAllCustomers();


    }

}
