//+------------------------------------------------------------------+
//|                                                          dut.mq4 |
//|                        Copyright 2016, MetaQuotes Software Corp. |
//|                                             https://www.mql5.com |
//+------------------------------------------------------------------+
#property copyright "Copyright 2016, MetaQuotes Software Corp."
#property link      "https://www.mql5.com"
#property version   "1.00"
#property strict

#define MAGICMA 09000

//--- input parameters
input float    buyWeight=0.7;
input float    sellWeight=0.7;
input int      buyDays=1;
input int      sellDays=1;
input float    lots=1;
;
double SB_openbuy,SB_opensell;
int res=0;
bool BUYme=false;
bool SELLme=false;
//+------------------------------------------------------------------+
//| Expert initialization function                                   |
//+------------------------------------------------------------------+
int OnInit()
  {
//---


//---
   return(INIT_SUCCEEDED);
  }
//+------------------------------------------------------------------+
//| Expert deinitialization function                                 |
//+------------------------------------------------------------------+
int deinit()
  {
//----

//----
   return(0);
  }
//+------------------------------------------------------------------+
//| expert start function |
//+------------------------------------------------------------------+
int start()
  {

//----
   for(int i=OrdersTotal(); i>=0; i--)
     {
      if(OrderSelect(i,SELECT_BY_POS,MODE_TRADES)==true)
         if(OrderMagicNumber()!=MAGICMA) continue;
      if(OrderSymbol()!=Symbol()) continue;
        {

         if(OrderType()==OP_BUY) {SB_openbuy++;}

         if(OrderType()==OP_SELL) {SB_opensell++;}
        }
     }
//-----
   double today_open,sellRange,buyRange,highestHigh,highestClose,lowestLow,lowestClose;

   highestHigh=High[iHighest(0,PERIOD_D1,MODE_HIGH,sellDays,0)];
   highestClose=Close[iHighest(0,PERIOD_D1,MODE_CLOSE,sellDays,0)];
   lowestLow=Low[iLowest(0,PERIOD_D1,MODE_LOW,sellDays,0)];
   lowestClose=Close[iLowest(0,PERIOD_D1,MODE_CLOSE,sellDays,0)];
  
   if(highestHigh-lowestClose>=highestClose-lowestLow)
     {
      sellRange=highestHigh-lowestClose;
     }
   else
     {
      sellRange=highestClose-lowestLow;
     }

   highestHigh=High[iHighest(0,PERIOD_D1,MODE_HIGH,buyDays,0)];
   highestClose=Close[iHighest(0,PERIOD_D1,MODE_CLOSE,buyDays,0)];
   lowestLow=Low[iLowest(0,PERIOD_D1,MODE_LOW,buyDays,0)];
   lowestClose=Close[iLowest(0,PERIOD_D1,MODE_CLOSE,buyDays,0)];
   
   if(highestHigh-lowestClose>=highestClose-lowestLow)
     {
      buyRange=highestHigh-lowestClose;
     }
   else
     {
      buyRange=highestClose-lowestLow;
     }

   today_open=iOpen(Symbol(),PERIOD_D1,0);

//----------

   if(iOpen(Symbol(),0,0)>(today_open+buyRange*buyWeight))
     {
      BUYme=true;
     }
   if(iOpen(Symbol(),0,0)<(today_open-sellRange*sellWeight))
     {
      SELLme=true;
     }
//----------------------------------------
   if(SB_openbuy==0){Open_Oderbuy();}
   if(SB_opensell==0){Open_Odersell();}
   if(SB_openbuy==1){Pips();}
   if(SB_opensell==1){Pips();}
//Print(SB_openbuy,SB_opensell,BUYme,SELLme);
//----
   SB_openbuy=0;SB_opensell=0;BUYme=false;SELLme=false;
//----
   return(0);
  }
//+------------------------------------------------------------------+
void Open_Oderbuy()
  {
   if(BUYme==true)
     {
      res=OrderSend(Symbol(),OP_BUY,lots,Ask,100,0,0,"",MAGICMA,0,Blue);
     }
  }
///////

void Open_Odersell()
  {
   if(SELLme==true)
     {
      res=OrderSend(Symbol(),OP_SELL,lots,Bid,100,0,0,"",MAGICMA,0,Blue);
     }
  }
//

//
void Pips()
  {
   for(int e=OrdersTotal();e>=0;e--)
     {
      if(OrderSelect(e,SELECT_BY_POS,MODE_TRADES)==true)
         if(OrderMagicNumber()!=MAGICMA) continue;
      if(OrderSymbol()!=Symbol()) continue;
        {

         if(OrderType()==OP_BUY)
           {
            if(SELLme==true)

              {OrderClose(OrderTicket(),OrderLots(),Bid,100,Red);}
           }
         if(OrderType()==OP_SELL)
           {
            if(BUYme==true)

              {OrderClose(OrderTicket(),OrderLots(),Ask,100,Red);}
           }
        }
     }
  }
//+------------------------------------------------------------------+
