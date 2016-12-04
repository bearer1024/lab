using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace awtCw2Q3
{
    public class Student
    {
        private string studentName;
        private int studentNo;

        public Student(string studentName,int studentNo)
        {
            this.StudentName = studentName;
            this.StudentNo = studentNo;
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

        public int StudentNo
        {
            get
            {
                return studentNo;
            }

            set
            {
                studentNo = value;
            }
        }
    }
   
}