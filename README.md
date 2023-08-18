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

  
