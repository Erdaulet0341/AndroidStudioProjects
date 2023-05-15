# Remote Shop
Application for buying and selling any products for clients and sellers.
Backend is in [django rest framework](https://github.com/Erdaulet0341/android-back). This django project unsafe(authorization is not with tokens, all passwords not hashed), because the main focus was allocated for android side, not are django side))


In this application 3 role, client, seller and admin. For full project used 11 activity, 24 fragment, 8 adapter and 8 data classes.

# Welcome page

![]()

# Client mode

1) Home page. In this page clients see all products of sellers, popular products and products by category.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(19).gif)

2) Product details. In this page name, price, description, company name(seller), rating, comments and add to like, add to cart buttons, add comment buttons.
When client clicked add comment button opened fragment with one input view and autocomplete input view. After adding commect and rating changed rating of this product.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(17).gif)

3) All products. In this page client can filter products by category, company and price from min to max. Sort products by price and search by name.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(18).gif)

4) Liked products. Client can add products to liked products.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(20).gif)

5) Cart. If client added products to the cart this products is displayed in this page. From this page client can send message(order) for the seller.
First icon of cart shooping_cart, after sending message icon will be clock, if seller accepted this order icon of this cart changed to check else to block.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(21).gif)

6) Profile and setting pages. In profile page client can change username, email, city and password. In setting page terms to use, help, privacy policy pages and log out button.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(22).gif)

# Seller mode

1) Home page. List of seller products. On the top seacrh view for searching products. After clicking product opened product details page. In this page seller edit product.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(12).gif)

2) Add product. Seller added product only admins created categories.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(15).gif)

3) Profile. In profile page client can change username, email, sompany name and password.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(13).gif)

4) Messages(orders). Displayed messages which clients send to your products.

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(14).gif)

# Admin mode

Admins can see all sellers and clients and can delete them

![](https://github.com/Erdaulet0341/AndroidStudioProjects/blob/master/RemoteShop/Readme/ezgif.com-resize%20(16).gif)
