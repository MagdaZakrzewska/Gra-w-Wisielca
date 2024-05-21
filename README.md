2. Gra w Wisielca
## Spis treści

    - Opis projektu
    - Funkcjonalności
    - Instrukcja obsługi
    - Struktura repozytorium

## Opis projektu

Projekt "Gra Wisielec" to implementacja popularnej gry słownej, w której celem gracza jest odgadnięcie ukrytego słowa poprzez podawanie liter. Gra została zrealizowana w języku Java z wykorzystaniem biblioteki JavaFX do stworzenia interfejsu graficznego.

## Funkcjonalności

    - Ekran główny:
        Powitanie użytkownika.
        Opcja rozpoczęcia nowej gry.
        Dostęp do bazy słów.
        Wyjście z gry.

    Nowa Gra:
        Losowanie słowa z bazy słów.
        Wyświetlanie stanu gry (odgadnięte litery, pozostałe próby, aktualny obraz wisielca).
        Możliwość zgadywania liter.
        Informacja o poprawności zgadywania.
        Statystyki rozgrywki (ilość zwycięstw, porażek, średnia liczba prób).
        Przywracanie gry do stanu początkowego po zakończeniu rozgrywki.
        
    Baza Słów:
        Wyświetlanie listy dostępnych słów.
        Dodawanie nowych słów do bazy.
        Usuwanie istniejących słów z bazy.

### Wymagania

- Java Development Kit (JDK) w wersji 8 lub nowszej.
- Biblioteka JavaFX.

## Dodatkowe paczki

Projekt wymaga biblioteki JavaFX. Upewnij się, że masz ją zainstalowaną i odpowiednio skonfigurowaną w swoim środowisku programistycznym. Jeżeli jej nie posiadasz możesz ją pobrać ze strony 
https://openjfx.io/ a pod tym linkiem: https://openjfx.io/openjfx-docs/ znajdziesz dokładną instrukcję konfiguracji javafx dla visual studio code.

### Uruchomienie aplikacji

1. Pobierz projekt z repozytorium GitHub.
2. Upewnij się, że masz zainstalowany JDK oraz JavaFX.
3. W katalogu głównym projektu otwórz terminal i skompiluj projekt, używając następujących poleceń:
   ```bash
   javac -d bin -sourcepath src src/HangmanGame/Main.java
4. przejdz do katalogu 'bin' 
    cd bin
5. Uruchom aplikację
    java HangmanGame.Main

### Struktura projektu

    - 'src/HangmanGame/Main.java': Punkt wejścia do aplikacji.
    - 'src/HangmanGame/MainGUI.java': Klasa odpowiadająca za główny interfejs użytkownika aplikacji. Zawiera menu główne, z którego użytkownik może przejść do gry lub zarządzać bazą słów.
    - 'src/HangmanGame/NowaGra.java': Klasa reprezentująca grę Wisielec. Zarządza logiką gry, interfejsem użytkownika i wyświetlaniem obrazów wisielca w zależności od postępów w grze.
    - 'src/HangmanGame/BazaSlow.java': Klasa odpowiedzialna za zarządzanie bazą słów używanych w grze. Pozwala użytkownikowi dodawać i usuwać słowa z bazy danych.
    - 'images/hangman0.png do images/hangman8.png': Obrazy używane do wyświetlania postępu wisielca.