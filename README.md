# Galgeleg
DTU Galgeleg project

---------Kilder--------
//Align to Text Views\\
https://stackoverflow.com/questions/15575122/how-to-align-two-textviews-within-linearlayout-cell/15575222

//Loading ikon\\
https://stackoverflow.com/questions/5442183/using-the-animated-circle-in-an-imageview-while-loading-stuff

//Array list and spinner\\
https://stackoverflow.com/questions/4029261/populating-spinner-directly-in-the-layout-xml

//GridLayout\\
https://stackoverflow.com/questions/45644828/how-do-i-fill-a-grid-with-imagebuttons-programmatically

//RecyclerView\\
https://developer.android.com/guide/topics/ui/layout/recyclerview


>FONTS
//PWFlymetothemoon Font by Peax Webdesign
https://www.1001fonts.com/pwflymetothemoon-font.html
//john F Sebastian Lettering Font\\
https://www.1001fonts.com/johnfsebastian-lettering-font.html


KRAV TIL GALGELEG III
K1 - App’en skal kunne installeres på og fungere på en telefon med Android 7.0
      (i app/build.gradle: minSdkVersion 24 eller lavere)
> Done

K2 - Der skal være mindst 5 skærmbilleder hvor du selv har implementeret layout i XML. Det skal være nogenlunde pænt.
> Der er mindst 5 skærmbilleder
> Hovedmenu, Nyt spil, vinder og taber skærmbilleder, highscore skærmbillede, hjælp skærmbillede.
!!Kan evt. tilføje loading skærmbillede

//!! LAYOUT IDE !!\\ 
> læg hangman png filerne i en pdf og eksportér til remarkable
> tegn på den med remarkable og få dem til at ligne 
> eksporter dem tilbage og få den i hangman res
>VOILÁ
> GØR DET SAMME MED HOVEDMENU'S KNAPPERNE
> Find skribbled font til edit text views
> og få dem til at passe ind i spillet.


K3 - De generelle retningslinjer for brugerinteraktion på Android-platformen bør være nogenlunde overholdt - herunder tilbagenavigering eller opnavigering
>Tilbagenavigering er implementeret

K4 - Der benyttes netværkskommunikation og flertrådet programmering (f.eks. hente en liste af ord fra DR eller fra et online regneark)
>Done i NytSpilFragment.java

K5 - Data skal gemmes lokalt (f.eks. med PreferenceManager)
>Done i HighscoreSaver.java

K6 - Der skal være en liste (f.eks. et RecyclerView der viser en highscoreliste).

      Listens elementer har flere varierende views (f.eks. scoren, ordet og tidspunktet - 
      det gælder ikke at formatere det hele i én enkelt streng og bare vise én streng per listeelement)
      
>Det fundamentale er lavet, men en highscore skal have flere attributter
      
K7 - Der skal kunne vælges noget fra en liste (f.eks. at ordet der skal gættes kan vælges fra en liste)
>Kravet er lidt utydeligt, men man kan vælge sværhedsgrad fra en spinner i NytSpilFragmentet.

K8 - Der skal være flere ‘medrivende’ elementer, der engagerer brugeren og motiverer ham til at fortsætte med at spille. Mindst 2 af følgende 3 punkter er opfyldt:

      *Du afspiller en lyd når man har vundet eller tabt
      *Du viser en animation
      *Du bruger et 3. parts bibliotek - f.eks. viser konfetti når spilleren har vundet spillet, eller en lottie effekt (f.eks. fra lottiefiles.com)
>Done, der er lavet konfetti og musik når man vinder
>Tilføj eventuelt animation og lyd når man dør.
      
K9 - App’ens navn skal starte med dit studienummer (ret i values/strings.xml så der står f.eks. <string name="app_name">s123456 Sørens Super Galgeleg</string>)
>Done

K10 - Udover kildekoden skal du også aflevere en binær APK-fil.

      Brug Build | Build APK(s). 
      Kopier app/build/outputs/apk/debugapp-debug.apk ind i roden af ZIP-filen du afleverer
      
K11 - Projektet er versioneret - du skal vedlægge .git mappen med historik i afleveringen.

      Versionsloggen skal løbe igennem hele projektperioden og vise de løbende forbedringer og ændringer.
