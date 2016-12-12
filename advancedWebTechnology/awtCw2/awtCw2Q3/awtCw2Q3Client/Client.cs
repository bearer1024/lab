using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using awtCw2Q3Client.IdentifyTransportServiceReference;
using awtCw2Q3Client.BookServiceReference;
using awtCw2Q3Client.SpecifyTimeAndPlaceServiceReference;

namespace awtCw2Q3Client
{
    class Client
    {
        static void Main(string[] args)
        {
            Console.WriteLine("please input studentNumber, orderDate, orignPlace, destionPlace, studentCardNumber");
            try
            {
                string inputs = Console.ReadLine();
                string[] splitedInputs = inputs.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                Int32 studentNo = Convert.ToInt32(splitedInputs[0]);
                Int32 orderDate = Convert.ToInt32(splitedInputs[1]);
                string orignPlace = splitedInputs[2];
                string destinationPlace = splitedInputs[3];
                Int32 studentCardNumber = Convert.ToInt32(splitedInputs[4]);

                

                IdentifyTransportServiceClient identifyClient = new IdentifyTransportServiceClient();
                BookingServiceClient bookingClient = new BookingServiceClient();
               
                SpecifyTimeAndPlaceClient specifyTimeAndPlaceClient = new SpecifyTimeAndPlaceClient();
                Int32 specifyRecordNo = specifyTimeAndPlaceClient.SpecifyTimeAndPlace(studentNo, orderDate, orignPlace, destinationPlace, studentCardNumber);
                string vehicleType = identifyClient.IdentifyTransport(orignPlace, destinationPlace);
                Int32 bookingConfirmationNumber = bookingClient.booking(specifyRecordNo, vehicleType, studentNo, studentCardNumber);

                Console.WriteLine("specifyRecordNo is\t" + specifyRecordNo);
                Console.WriteLine("vehicleType is\t" + vehicleType);
                Console.WriteLine("bookingConfirmationNumber is\t" + bookingConfirmationNumber);

                Console.ReadLine();
            }
            catch(Exception e)
            {
                Console.WriteLine(e.StackTrace);
                Console.WriteLine("maybe the input format is wrong\t");

            }

            
        }
    }
}
