SELECT
       Y.OrderMonth,   Y.TotalOrders,  Y.TotalAmount,
       Z.OrdCustFK,  Z.CustCompany,    Z.CustOrdTotal, Z.CustSalesTotal


FROM
     (SELECT
             OrdDate,
             DATE_FORMAT(OrdDate,'%M %Y') AS OrderMonth,
             COUNT(OrderID) AS TotalOrders,
             SUM(OrdGrandTotal) AS TotalAmount
      FROM Orders
      WHERE OrdConfirmed = 1
      GROUP BY DATE_FORMAT(OrdDate,'%m%y')
      ORDER BY DATE_FORMAT(OrdDate,'%Y%m') DESC)
         Y INNER JOIN
         (SELECT
                 DATE_FORMAT(OrdDate,'%M %Y') AS CustMonth,
                 OrdCustFK,
                 CustCompany,
                 COUNT(OrderID) AS CustOrdTotal,
                 SUM(OrdGrandTotal) AS CustSalesTotal
          FROM Orders INNER JOIN CustomerDetails ON OrdCustFK = CustomerID
          WHERE OrdConfirmed = 1
          GROUP BY DATE_FORMAT(OrdDate,'%m%y'), OrdCustFK
          ORDER BY SUM(OrdGrandTotal) DESC)
             Z ON Z.CustMonth = Y.OrderMonth

GROUP BY DATE_FORMAT(OrdDate,'%Y%m')
ORDER BY DATE_FORMAT(OrdDate,'%Y%m') DESC