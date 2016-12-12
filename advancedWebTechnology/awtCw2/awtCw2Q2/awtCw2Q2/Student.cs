using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace awtCw2Q2
{
    public class Student
    {
        private string studentName;


        public Student(string studentName)
        {
            this.StudentName = studentName;
        }

        public string StudentName
        {
            get
            {
                return studentName;
            }

            set
            {
                studentName = value;
            }
        }
    }

    
}