Изучите классы реализации коллекций и заполните следующую таблицу
-----------------------------------------------------------------

                      | Ordering |  Random Access | Key-Value Pairs | Allows Duplicates | Allows Null Values | Thread Safe | Blocking Operations
----------------------|----------|----------------|-----------------|-------------------|--------------------|-------------|--------------------
ArrayList             | Yes      | Yes            | No              | Yes               | Yes                | No          | No
LinkedList            | Yes      | No             | No              | Yes               | Yes                | No          | No
Vector                | Yes      | Yes            | No              | Yes               | Yes                | Yes         | Yes
Stack                 | Yes      | No             | No              | Yes               | Yes                | Yes         | Yes
HashSet               | No       | No             | No              | No                | Only 1             | No          | No
TreeSet               | Yes      | No             | No              | No                | Only 1             | No          | No
LinkedHashSet         | Yes      | No             | No              | No                | Only 1             | No          | No
PriorityQueue         | No       | No             | No              | Yes               | No                 | No          | No
PriorityBlockingQueue | No       | No             | No              | Yes               | No                 | No          | Yes
LinkedBlockingQueue   | Yes      | No             | No              | Yes               | No                 | Yes         | Yes
HashMap               | No       | No             | Yes             | Not for keys      | 1 key, all values  | No          | No
TreeMap               | Yes      | No             | Yes             | Not for keys      | for values only    | No          | No
LinkedHashMap         | Yes      | No             | Yes             | Not for keys      | 1 key, all values  | No          | No
