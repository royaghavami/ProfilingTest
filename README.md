# ProfilingTest

## بخش اول
همانطور که مشاهده میکنید، با انجام profiling بر روی JavaCup مشاهده میکنیم که بیشترین مصرف منابع را تابع temp دارد. در ادامه اسکرین شات های مربوطه را مشاهده می کنید:

![image](https://github.com/royaghavami/ProfilingTest/assets/59202308/ab91a9a0-2839-4543-9044-49660a071e6b)

![1](https://github.com/royaghavami/ProfilingTest/assets/59202308/7db5af82-61e3-4e40-b33f-1fb3e52fd574)


همانطور که مشاهده می کنید، در خط 29 ام بیشترین میزان مصرف cpu time را داریم:

![2](https://github.com/royaghavami/ProfilingTest/assets/59202308/a431cff8-cfa1-4c81-b853-edc8c98513da)

#### اسکرین شات CPU flame graph
![4](https://github.com/royaghavami/ProfilingTest/assets/59202308/50a09652-995d-4b43-b320-93ed2b016ea6)

#### اسکرین شات Call tree
![5](https://github.com/royaghavami/ProfilingTest/assets/59202308/11685dd6-bdcb-4b31-b63c-08d0c2976df4)

#### اسکرین شات Performance charts
همانطور که در این اسکرین شات مشاهده می کنید، در این اسکرین شات چارت هایی از قبیل CPU Time، Memmory Heap و... مشاهده می شود (موارد دیگر در سمت چپ لیست شده اند و در فایل اسنپ شات اپلود شده قرار دارند):
![11](https://github.com/royaghavami/ProfilingTest/assets/59202308/7461d8f2-b3d7-44ae-a79a-5288f7c0c41d)

![12](https://github.com/royaghavami/ProfilingTest/assets/59202308/2e48ca22-f00e-4a40-b3a5-371ad7fcdc90)

### در ادامه به بهبودهایی که انجام شده است می پردازیم:
#### 1- در مورد تابع temp()
-	برای بهبود استفاده از حافظه (memory usage) در کد، از انواع داده های اولیه (primitive data types) به جای اشیاء استفاده میکنیم. اینکار می تواند تا حد زیادی باعث کاهش سربار حافظه شود. 
-	علاوه بر این، می توانیم از متد ()sigureCapacity در ArrayList برای تخصیص حافظه مورد نیاز از قبل استفاده کرده و بنابراین نیاز به تغییر اندازه آرایه را کاهش می دهیم.
  - البته می توان تنها از یک loop استفاده کرد که موجب کاهش تعداد تکرارها و بهبود عملکرد می شود.
![13](https://github.com/royaghavami/ProfilingTest/assets/59202308/0c1e7d5a-e7f9-4f2f-aa08-5045cf27569a)
![13-1](https://github.com/royaghavami/ProfilingTest/assets/59202308/03352a28-49c4-486d-94f2-1955f9154306)


#### در کد اصلاح شده بالا:
- از نوع genericعه  ArrayList برای جلوگیری از جعبه‌گشایی/جعبه‌گشایی (Boxing/unboxing) غیرضروری استفاده می‌شود.
- متد ()sigureCapacity برای تخصیص حافظه از پیش، بر اساس تعداد کل عناصر مورد انتظار (totalNumbers) استفاده می شود.
- تعداد لوپ ها را به یکی کاهش دادیم.

#### 2- در مورد تابع eval()
برای بهبود نمایه سازی و استفاده از منابع این کد، می توانیم چند تغییر ایجاد کنیم:
-  استفاده از عملگرهای منطقی: به جای استفاده از چند دستور مقایسه، می توانیم از عملگرهای منطقی (&& و ||) برای ترکیب conditionها استفاده کنیم. این باعث کاهش تعداد عملیات انجام شده می شود.
- اجتناب از محاسبات غیر ضروری: ما فقط باید i * i و j * j را یک بار محاسبه کنیم و نه چندین بار.
- استفاده از StringBuilder برای الحاق رشته ها: به جای استفاده از چندین System.out.println()، می توانیم از StringBuilder برای ساخت موثر رشته خروجی استفاده کنیم.

  
![14](https://github.com/royaghavami/ProfilingTest/assets/59202308/9782d399-4716-480d-8673-5425dc241c0d)

## بخش دوم
برای این بخش، کد مربوط به دنباله فیبوناچی (که در کلاس FibonacciSequence تعریف شده است) را پیاده سازی کردیم که در ادامه مشاهده می کنید:

![16](https://github.com/royaghavami/ProfilingTest/assets/59202308/bfda680a-cb8b-4b0e-8469-537b7955cfc6)

همانطور که مشاهده می کنید، 50 عدد اول این دنباله به کمک این کد تولید می شود. در این کد، دنباله فیبوناچی از یک الگوریتم بازگشتی برای محاسبه اعداد فیبوناچی استفاده می کند. با این حال، این پیاده سازی دارای پیچیدگی زمانی نمایی است، به این معنی که محاسبه اعداد فیبوناچی بزرگتر زمان قابل توجهی خواهد داشت. 
درواقع، فراخوانی های بازگشتی برای محاسبه مقادیر دنباله فیبوناچی منجر به محاسبات اضافی زیادی می شود. به عنوان مثال، هنگام محاسبه فیبوناچی (4)، کد فیبوناچی (3) را دو بار، یک بار به عنوان بخشی از فیبوناچی (4) و یک بار به عنوان بخشی از فیبوناچی (3) محاسبه می کند. این افزونگی به صورت تصاعدی با افزایش مقدار n افزایش می یابد.

![1](https://github.com/royaghavami/ProfilingTest/assets/59202308/d3235476-0b5f-4ade-bb96-ceebddf67f05)

![2](https://github.com/royaghavami/ProfilingTest/assets/59202308/e3c2da5f-343b-4856-9d27-78631c5ea7ad)

![4](https://github.com/royaghavami/ProfilingTest/assets/59202308/14ce563d-7111-46c1-aab1-044f8f1f15f7)

![5](https://github.com/royaghavami/ProfilingTest/assets/59202308/67d1aef8-6649-45d5-8c64-aacc290e78f8)

![6](https://github.com/royaghavami/ProfilingTest/assets/59202308/6f8b5e48-adc4-41e9-a524-6b6317259def)

![7](https://github.com/royaghavami/ProfilingTest/assets/59202308/6c017787-5532-4866-8c2c-428425f97b1d)

![9](https://github.com/royaghavami/ProfilingTest/assets/59202308/3b3f5a63-5e68-48d0-9efb-c7ce517c9965)



و به طور کلی در summary این profiling داریم:



![8](https://github.com/royaghavami/ProfilingTest/assets/59202308/0fe86ffc-37b0-482f-8b42-09f7e03a8bde)





#### برای بهبود مصرف منابع و زمان اجرا، می‌توانیم روش فیبوناچی را برای استفاده از رویکرد کارآمدتر، مانند برنامه‌نویسی پویا با حافظه‌گذاری (memorization)، اصلاح کنیم (که کد مربوط به آن در کلاس NewFibonacciSequence قرار دارد):

![17](https://github.com/royaghavami/ProfilingTest/assets/59202308/b79b45ee-0d3e-4124-9eb5-a0cf371973ab)


تکنیک Memoization تکنیکی است که در آن نتیجه فراخوانی تابع در حافظه پنهان ذخیره می شود، به طوری که اگر تابع دوباره با همان پارامترها فراخوانی شود، به جای محاسبه مجدد نتیجه، می توان نتیجه ذخیره شده را برگرداند.
در این کد اصلاح‌شده، ما یک تکنیک حافظه‌سازی را با استفاده از HashMap برای ذخیره اعداد فیبوناچی محاسبه‌شده قبلی معرفی می‌کنیم. با انجام این کار، می توانیم از محاسبات اضافی اجتناب کنیم و زمان اجرا و مصرف منابع را به طور قابل توجهی بهبود دهیم.


![11](https://github.com/royaghavami/ProfilingTest/assets/59202308/ada9661f-7253-468a-ae9b-16f31e49534d)


![12](https://github.com/royaghavami/ProfilingTest/assets/59202308/c4de74ac-cf25-4918-92c7-d59fc760b2c2)


![13](https://github.com/royaghavami/ProfilingTest/assets/59202308/00a76f28-ca28-4001-8205-24949c813c25)


![14](https://github.com/royaghavami/ProfilingTest/assets/59202308/6cc0e4f2-f282-4859-aad1-45d3ad429dbb)


و به طور کلی در summary این profiling داریم:


![14](https://github.com/royaghavami/ProfilingTest/assets/59202308/bcd2cb76-13dd-4e4c-8058-5b91a8f738d5)




