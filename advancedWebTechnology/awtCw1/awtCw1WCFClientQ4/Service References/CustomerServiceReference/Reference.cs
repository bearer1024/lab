﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace awtCw1WCFClientQ4.CustomerServiceReference {
    using System.Runtime.Serialization;
    using System;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="Customer", Namespace="http://schemas.datacontract.org/2004/07/awtCw1WCF")]
    [System.SerializableAttribute()]
    public partial class Customer : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int CustomerCardNoField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int CustomerIdField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string CustomerNameField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int CustomerCardNo {
            get {
                return this.CustomerCardNoField;
            }
            set {
                if ((this.CustomerCardNoField.Equals(value) != true)) {
                    this.CustomerCardNoField = value;
                    this.RaisePropertyChanged("CustomerCardNo");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int CustomerId {
            get {
                return this.CustomerIdField;
            }
            set {
                if ((this.CustomerIdField.Equals(value) != true)) {
                    this.CustomerIdField = value;
                    this.RaisePropertyChanged("CustomerId");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string CustomerName {
            get {
                return this.CustomerNameField;
            }
            set {
                if ((object.ReferenceEquals(this.CustomerNameField, value) != true)) {
                    this.CustomerNameField = value;
                    this.RaisePropertyChanged("CustomerName");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="CustomerServiceReference.ISCustomerService")]
    public interface ISCustomerService {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ISCustomerService/getCustomerByName", ReplyAction="http://tempuri.org/ISCustomerService/getCustomerByNameResponse")]
        awtCw1WCFClientQ4.CustomerServiceReference.Customer getCustomerByName(string name);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ISCustomerService/getCustomerByName", ReplyAction="http://tempuri.org/ISCustomerService/getCustomerByNameResponse")]
        System.Threading.Tasks.Task<awtCw1WCFClientQ4.CustomerServiceReference.Customer> getCustomerByNameAsync(string name);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ISCustomerService/addNewCustomer", ReplyAction="http://tempuri.org/ISCustomerService/addNewCustomerResponse")]
        bool addNewCustomer(int customerId, string customerName, int customerCardNo);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ISCustomerService/addNewCustomer", ReplyAction="http://tempuri.org/ISCustomerService/addNewCustomerResponse")]
        System.Threading.Tasks.Task<bool> addNewCustomerAsync(int customerId, string customerName, int customerCardNo);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ISCustomerService/countCustomerNumber", ReplyAction="http://tempuri.org/ISCustomerService/countCustomerNumberResponse")]
        int countCustomerNumber();
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ISCustomerService/countCustomerNumber", ReplyAction="http://tempuri.org/ISCustomerService/countCustomerNumberResponse")]
        System.Threading.Tasks.Task<int> countCustomerNumberAsync();
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ISCustomerService/getAllCustomers", ReplyAction="http://tempuri.org/ISCustomerService/getAllCustomersResponse")]
        awtCw1WCFClientQ4.CustomerServiceReference.Customer[] getAllCustomers();
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ISCustomerService/getAllCustomers", ReplyAction="http://tempuri.org/ISCustomerService/getAllCustomersResponse")]
        System.Threading.Tasks.Task<awtCw1WCFClientQ4.CustomerServiceReference.Customer[]> getAllCustomersAsync();
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ISCustomerServiceChannel : awtCw1WCFClientQ4.CustomerServiceReference.ISCustomerService, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class SCustomerServiceClient : System.ServiceModel.ClientBase<awtCw1WCFClientQ4.CustomerServiceReference.ISCustomerService>, awtCw1WCFClientQ4.CustomerServiceReference.ISCustomerService {
        
        public SCustomerServiceClient() {
        }
        
        public SCustomerServiceClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public SCustomerServiceClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public SCustomerServiceClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public SCustomerServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        public awtCw1WCFClientQ4.CustomerServiceReference.Customer getCustomerByName(string name) {
            return base.Channel.getCustomerByName(name);
        }
        
        public System.Threading.Tasks.Task<awtCw1WCFClientQ4.CustomerServiceReference.Customer> getCustomerByNameAsync(string name) {
            return base.Channel.getCustomerByNameAsync(name);
        }
        
        public bool addNewCustomer(int customerId, string customerName, int customerCardNo) {
            return base.Channel.addNewCustomer(customerId, customerName, customerCardNo);
        }
        
        public System.Threading.Tasks.Task<bool> addNewCustomerAsync(int customerId, string customerName, int customerCardNo) {
            return base.Channel.addNewCustomerAsync(customerId, customerName, customerCardNo);
        }
        
        public int countCustomerNumber() {
            return base.Channel.countCustomerNumber();
        }
        
        public System.Threading.Tasks.Task<int> countCustomerNumberAsync() {
            return base.Channel.countCustomerNumberAsync();
        }
        
        public awtCw1WCFClientQ4.CustomerServiceReference.Customer[] getAllCustomers() {
            return base.Channel.getAllCustomers();
        }
        
        public System.Threading.Tasks.Task<awtCw1WCFClientQ4.CustomerServiceReference.Customer[]> getAllCustomersAsync() {
            return base.Channel.getAllCustomersAsync();
        }
    }
}
