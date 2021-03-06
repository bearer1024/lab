﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace awtCw2Q3Client.IdentifyTransportServiceReference {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="IdentifyTransportServiceReference.IdentifyTransportService")]
    public interface IdentifyTransportService {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IdentifyTransportService/IdentifyTransport", ReplyAction="http://tempuri.org/IdentifyTransportService/IdentifyTransportResponse")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="vehicleType")]
        string IdentifyTransport(string orignPlace, string destinationPlace);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IdentifyTransportService/IdentifyTransport", ReplyAction="http://tempuri.org/IdentifyTransportService/IdentifyTransportResponse")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="vehicleType")]
        System.Threading.Tasks.Task<string> IdentifyTransportAsync(string orignPlace, string destinationPlace);
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface IdentifyTransportServiceChannel : awtCw2Q3Client.IdentifyTransportServiceReference.IdentifyTransportService, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class IdentifyTransportServiceClient : System.ServiceModel.ClientBase<awtCw2Q3Client.IdentifyTransportServiceReference.IdentifyTransportService>, awtCw2Q3Client.IdentifyTransportServiceReference.IdentifyTransportService {
        
        public IdentifyTransportServiceClient() {
        }
        
        public IdentifyTransportServiceClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public IdentifyTransportServiceClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public IdentifyTransportServiceClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public IdentifyTransportServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        public string IdentifyTransport(string orignPlace, string destinationPlace) {
            return base.Channel.IdentifyTransport(orignPlace, destinationPlace);
        }
        
        public System.Threading.Tasks.Task<string> IdentifyTransportAsync(string orignPlace, string destinationPlace) {
            return base.Channel.IdentifyTransportAsync(orignPlace, destinationPlace);
        }
    }
}
