## 1. The built-in methods

### Create / Update
    save(account): returns the saved account.

    saveAll(list): saves many at once.

### Read

    findById(id): returns Optional<Account>. 

    findAll(): returns List<Account>.

    existsById(id): returns boolean.

    count(): returns long.

### Delete
    
    deleteById(id)

    delete(account)

    deleteAll()

### Sorting and paging (bonus)

    findAll(Sort.by("balance").descending()): sorted list.

    findAll(PageRequest.of(0, 10)): first 10 results, useful once you have thousands of rows.


## 2. How save() decides between insert and update

save looks at the **ID**. If a row with that ID exists, it updates it. If not, it inserts it. Because you assign IDs yourself (like “ACC-1001”), Spring first runs a SELECT to check. That is why saving an account with an existing number silently overwrites it.