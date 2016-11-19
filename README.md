# HotelReservationSystem

# TO RUN
javac *.java
java ReservationSystem

#Main Idea

Each time mutators in Model are called, model calls view.drawOnUpdatedData(). This method will recursively update data in View and repaint them.

The basic architecture is ReservationSystem - Model - View.

View displays only one PanelXXX at any give time. When user triggers some event, such as press “Done” after reservation, View removes the current PanelXXX and display the PanelYYY user wants to see, such as PanelPress.


#Model CRC Card

Model


Guest


Room


Order




#View CRC Card

View extends JFrame -- View has a MyPanel currentPanel, which is switched according to user action. Initally, currentPanel is managerOrGuestPanel


PanelPrototype extends JPanel
- Every designed Panel inherits PanelPrototype


PanelManagerOrGuest extends PanelPrototype


PanelGuestSign extends PanelPrototype


Many other PanelXXX……… that all extends PanelPrototype


#Caution
Manager view’s “load” has no listener. I think it is more reasonable to load data from file as soon as program is executed. In order to not lose guest’s new orders, we shouldn’t load data from file again,
