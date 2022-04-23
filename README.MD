# Džáva

## Inheritance/Interface

### Kompozice

* Vhodné dávat přednost před dědičností
* Jiný objekt jako atribut v objektu (např. String ve třídě, Adresa v Osoba)

### Dědičnost

* Vazba _is a_ 
  * Circle _is a_ Shape
* `extends`
* Můžeme dědit pouze z jedné třídy

### Implementace

* Vazba _implements_
  * Circle _implements_ Computable
* `implements`
* Můžeme implementovat více rozhraní

## Knihovní třída

```java
// final – nemůžeme dědit
// privátní konstruktor – nelze tvořit instance
// všechny metody statické
public final class Library {
    private Library() {}
}
```

## Git příkazy

```txt
git init
git config user.name "<username>"
git config user.email <email>
git add .
git commit -m "msg"
git branch -M main
git remote add origin https://<username>@github.com/<username>/<reponame>.git
git push -u origin main
```
