using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace awtCw2Q2
{
    public class Card
    {
        int catdNumber;
        String cardHolderName;
        float cardBalance;

        public int CatdNumber
        {
            get
            {
                return catdNumber;
            }

            set
            {
                catdNumber = value;
            }
        }

        public string CardHolderName
        {
            get
            {
                return cardHolderName;
            }

            set
            {
                cardHolderName = value;
            }
        }

        public float CardBalance
        {
            get
            {
                return cardBalance;
            }

            set
            {
                cardBalance = value;
            }
        }
    }
}