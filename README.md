# ElevatorSystem

Repozytorium przedstawia zaimplemntowany system obsługi wind. <br />
Samemu może działać jako symulator, jednak nie powinno być problemu z podłączeniem większego systemu.<br />
Zostały zaimplementowane dwa algorytmy.<br />
Pierszy z nich jest podstawowy - winda odwiedza wszystkie pięrta w kolejności zgłoszeń.<br />
Powstał głównie w celach referencyjnych, aby było z czym porównać drugi algorytm.<br />
Drugi algorytm odwiedza pięrta po kolei, a nie w kolejności zgłoszeń.<br /> 
Jednak nie zatrzymuje się na danym piętrze, jeśli na nim oczekują tylko osoby,<br />
które chcą jechać w przeciwnym kierunku, niż aktualnie jedzie winda.<br />
W drugim algorytmie winda zmienia kierunek jazdy tak rzadko, jak to tylko możliwe.<br />
<br />
Wyniki porównawcze:<br />
<br />
Fifo<br />
Test 1<br />
Average waiting time: 2.691 Average driving time: 8.389<br />
19.966 steps per passenger<br />
Test 2<br />
Average waiting time: 11.665 Average driving time: 67.888<br />
1.161 steps per passenger<br />
Test 3<br />
Average waiting time: 10.887 Average driving time: 146.416<br />
0.889 steps per passenger<br />
Test 4<br />
Average waiting time: 20.829 Average driving time: 81.465<br />
20.162 steps per passenger<br />
Test 5<br />
Average waiting time: 742.991 Average driving time: 4986.225<br />
12.189 steps per passenger<br />
Test 6<br />
Average waiting time: 404.662 Average driving time: 4961.959<br />
11.48 steps per passenger<br />
Test 7<br />
Average waiting time: 1112.5448 Average driving time: 14769.8507<br />
8.7959 steps per passenger<br />
Test 8<br />
Average waiting time: 741.0119 Average driving time: 25053.3585<br />
7.4534 steps per passenger<br />
Test 9<br />
Average waiting time: 562.7718 Average driving time: 45986.8307<br />
7.503 steps per passenger<br />
<br />
Smarter<br />
Test 1<br />
Average waiting time: 4.198 Average driving time: 9.624<br />
19.97 steps per passenger<br />
Test 2<br />
Average waiting time: 4.378 Average driving time: 16.065<br />
1.033 steps per passenger<br />
Test 3<br />
Average waiting time: 14.69 Average driving time: 45.775<br />
0.292 steps per passenger<br />
Test 4<br />
Average waiting time: 27.941 Average driving time: 81.452<br />
20.226 steps per passenger<br />
Test 5<br />
Average waiting time: 133.408 Average driving time: 362.004<br />
4.319 steps per passenger<br />
Test 6<br />
Average waiting time: 138.942 Average driving time: 162.855<br />
0.719 steps per passenger<br />
Test 7<br />
Average waiting time: 70.8291 Average driving time: 292.9698<br />
2.1801 steps per passenger<br />
Test 8<br />
Average waiting time: 228.5118 Average driving time: 371.5789<br />
1.2506 steps per passenger<br />
Test 9<br />
Average waiting time: 95.7021 Average driving time: 204.9143<br />
0.0979 steps per passenger<br />
<br />
Każdy test zawierał 10 wind i miał następujące parametry:<br />
Test 1: 20 pięter, 1000 pasażerów, maksymalne opóźnienie wejścia pasażera: 20000<br />
Test 2: 20 pięter, 1000 pasażerów, maksymalne opóźnienie wejścia pasażera: 1000<br />
Test 3: 20 pięter, 1000 pasażerów, maksymalne opóźnienie wejścia pasażera: 100<br />
Test 4: 200 pięter, 1000 pasażerów, maksymalne opóźnienie wejścia pasażera: 20000<br />
Test 5: 200 pięter, 1000 pasażerów, maksymalne opóźnienie wejścia pasażera: 1000<br />
Test 6: 200 pięter, 1000 pasażerów, maksymalne opóźnienie wejścia pasażera: 100<br />
Test 7: 200 pięter, 10000 pasażerów, maksymalne opóźnienie wejścia pasażera: 20000<br />
Test 8: 200 pięter, 10000 pasażerów, maksymalne opóźnienie wejścia pasażera: 1000<br />
Test 9: 200 pięter, 10000 pasażerów, maksymalne opóźnienie wejścia pasażera: 100<br />
<br />
Czas średni nie pomija opóźnienia wejścia.<br />
Dla każdego testu ziarno generatora pseudolosowego jest takie samo przy danym uruchomieniu programu.<br />
<br />
Zaprojektowany system nie jest znacznie gorszy w normalnych warunkach, <br />
jednak zyskuje znaczną przewagę wraz ze wzrostem natężenia pasażerów.<br />
<br />
W celu kompilacji wszystkich plików należy wykonać polecenie <br />javac *.java<br /> będąc w katalogu projektu.<br />
Do tego wymagany jest zainstalowany kompilator języka java (a do uruchomienia maszyna wirtualna).<br />
Kompilacji i testów dokonywano na wersji java 17 (openjdk 17.0.2 2022-01-18 LTS).<br />
<br />
Podstawowym testem (który sprawdza, czy windy się zatrzymują tam gdzie powinny) jest test.txt, <br />
który należy przekierować na standardowe wejście (i ręcznie sprawdzić, czy cele windy pokrywaja się z aktualnymi piętrami).<br />
W celu uruchomienia i przekierowania standardowego wejścia należy wykonać polecenie:<br />
java ConsoleElevatorSystem < test.txt<br /> 
<br />
Aby uruchomić testy należy wykonać plik test2.sh (należy mu przedtem nadać prawa do wykonywania), który także wcześniej dokona rekompilacji wszystkich plików.<br />
W celu ręcznego uruchomienia testów należy wpisać polecenie: <br />java ElevatorSystemTest<br />

Została także napisana aplikacja konsolowa - w celu jej uruchomienia należy wpisać: <br />java ConsoleElevatorSystem<br />
W pierszej linii na wejsciu oczekuje liczby wind. Musi być to liczba większa od zera i co najwyżej równa 16.<br />
Obsługuje ona następujące polecenia:<br />
step - wykonanie kroku symulacji<br />
call <ID windy> <piętro wzywania> <kierunek jazdy> - wzywa konkretną windę do odebrania pasażera<br />
pickup <piętro wzywania> <kierunek jazdy> - wzywa optymalną windę do odebrania pasażera, wypisuje jej identyfikator<br />
update <ID windy> <piętro wzywania> <kierunek jazdy> - teleportuje windę na podane piętro<br />
add destination <ID windy> <piętro docelowe> - każde windzie jechać na podane piętro<br />
status - wypisuje informacje o położeniu i jeździe wind<br />
exit - wychodzi z programu.<br />




