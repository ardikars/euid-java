package euid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckTest {

    private static final long[][] LL = new long[][]{
            new long[]{4764011822499138043L, 5737642780979663628L},
            new long[]{8786479924207535777L, 520126398686943008L},
            new long[]{7686070465777987309L, 6924794180176062693L},
            new long[]{3284346015881131650L, 6588459724577918018L},
            new long[]{5510055547472027077L, 2341160268066945578L},
            new long[]{3385637683647773358L, 8092785792271624578L},
            new long[]{8471671506228083446L, 6911698028779137502L},
            new long[]{6649541120664456550L, 3079918716906306702L},
            new long[]{7250820595384558843L, 4032855217247336842L},
            new long[]{1226166403505830464L, 8763614063560259079L},
            new long[]{9063283512423700791L, 7099428816682951873L},
            new long[]{3721634810618976479L, 1218569080117076675L},
            new long[]{2373721387815249582L, 7727812080832617562L},
            new long[]{8618028844986379924L, 8832506674285573860L},
            new long[]{4970761470003428L, 8711908158889208630L},
            new long[]{1052785419311130855L, 3958956907335767503L},
            new long[]{9082917711005915433L, 6384604984789743251L},
            new long[]{1988683323504098616L, 9165793601801952129L},
            new long[]{6750167727093665895L, 3996702490536596559L},
            new long[]{2122451561409805298L, 2654106302756225897L},
            new long[]{5317834177245159626L, 6533684475718811686L},
            new long[]{8753829680621338094L, 8213887333235901544L},
            new long[]{5806933949917078625L, 7159240322887757628L},
            new long[]{2654874554163652789L, 8723662839836690773L},
            new long[]{6995631707308663528L, 7589954106288260775L},
            new long[]{3084740161439393838L, 8844864834007039832L},
            new long[]{7616751410021518248L, 4900320389304053705L},
            new long[]{802860523949183700L, 2509730678779213622L},
            new long[]{2006548014824610014L, 1913116124455698060L},
            new long[]{3254392781121171605L, 7401771621467104896L},
            new long[]{279174695103331830L, 4615465003843751116L},
            new long[]{868785073837313985L, 7747363219256420005L},
            new long[]{4243744957330829870L, 1102407338629439754L},
            new long[]{5978314982262818249L, 6715848832383123214L},
            new long[]{3709913996674707733L, 3557091630978011332L},
            new long[]{4169171859862744132L, 2773410325286993564L},
            new long[]{3272913466666440679L, 3337395162904024047L},
            new long[]{6739570547103231985L, 5401872616151839049L},
            new long[]{7215326387659898252L, 1088083010538027668L},
            new long[]{2565440780234152749L, 8574015273278175093L},
            new long[]{171045734464202981L, 4097718826036446040L},
            new long[]{2097710672024224281L, 1855845275746554196L},
            new long[]{1093599300836697797L, 7427212430270311567L},
            new long[]{259786063430802095L, 128583345929209181L},
            new long[]{4026622127198004932L, 869073416961809024L},
            new long[]{1802141934695993061L, 8349993664652681206L},
            new long[]{7142655301002953165L, 3096978425786645869L},
            new long[]{5726307652665315412L, 5611942376921560677L},
            new long[]{7784291693261623833L, 5645337780729197024L},
            new long[]{4761355813691085754L, 1833697349530106724L},
            new long[]{182557174267247737L, 3980821151041860026L},
            new long[]{1117239452619816636L, 6621102554648670910L},
            new long[]{1290062936834539503L, 2803746324541002810L},
            new long[]{3682183985930443916L, 2383118989469965091L},
            new long[]{8898822028677924442L, 422415317406344478L},
            new long[]{7602830762447031069L, 2852463572981412239L},
            new long[]{3712798639267275247L, 9013471086956906802L},
            new long[]{5865036534623631600L, 8705593786360530207L},
            new long[]{7716211826125540052L, 2618296487170040449L},
            new long[]{1726957302837064790L, 7842342718676633178L},
            new long[]{4166443707558854678L, 4116247620282275436L},
            new long[]{7889053464388742310L, 592021321121463117L},
            new long[]{5769878358227376826L, 8859496131954054526L},
            new long[]{8786793113069294524L, 7413948516840620514L},
            new long[]{5442244461804009504L, 8416756064416128931L},
            new long[]{5683337699308481701L, 2496457965940947613L},
            new long[]{2834979036976188802L, 6420670793454771543L},
            new long[]{6764591578390785239L, 1299933935167056486L},
            new long[]{1283006922276603564L, 4436079150577971923L},
            new long[]{9191411116065680810L, 5954756046518393837L},
            new long[]{2348975831441412635L, 1967655134368821042L},
            new long[]{361182812771813007L, 7452689822646291995L},
            new long[]{5678401214859061348L, 6047052707525226979L},
            new long[]{4567373327765422180L, 6878521987018736385L},
            new long[]{2888867215552476576L, 1905688729650012359L},
            new long[]{6802246446220544646L, 3816308071505160863L},
            new long[]{6727911532173577895L, 9069766109323508962L},
            new long[]{1788428149031338446L, 5683808330037611755L},
            new long[]{3427911447145207752L, 1568052597094171412L},
            new long[]{8911263852087549380L, 2704839703467710588L},
            new long[]{8231482292437740659L, 1793540495685593729L},
            new long[]{4240788735389497594L, 5697679069439865812L},
            new long[]{4177562442129474378L, 8601831708228912023L},
            new long[]{8379190436031623935L, 1898070258553753392L},
            new long[]{7110207392543868293L, 3699720988141320707L},
            new long[]{6484090040640922686L, 3098753981918135988L},
            new long[]{4707520277881436982L, 105510725291112670L},
            new long[]{5866839759522719583L, 3604771982296873599L},
            new long[]{241018703085939364L, 6128935905357257683L},
            new long[]{5398655646066356491L, 1088229606696719732L},
            new long[]{8388951461995694365L, 946433466066167692L},
            new long[]{6012032256697937774L, 6416524031717634273L},
            new long[]{4309381956035194744L, 9203543024748304975L},
            new long[]{6224713679440317432L, 7835791203858938820L},
            new long[]{3351428872255312234L, 6316349711424980928L},
            new long[]{7250089094521304931L, 336060173489389185L},
            new long[]{4317255778243508077L, 7325096897443593501L},
            new long[]{3530394022375622525L, 5575014542399735300L},
            new long[]{2200455884870251225L, 5824542085590820699L},
            new long[]{3243115015260386949L, 374552291682246434L},
            new long[]{8908741857448554755L, 3550900204399494343L},
            new long[]{555558376316442709L, 2427779511079802358L},
            new long[]{8344994755320722230L, 733049424893908583L},
            new long[]{6475824612360437325L, 1954625519999132588L},
            new long[]{1119948301935142194L, 8944030759434347542L},
            new long[]{9029625828181197779L, 5087962133034066000L},
            new long[]{6139118888261698571L, 1142078544641488812L},
            new long[]{519568507699548638L, 566957066853567810L},
            new long[]{1671843521810324098L, 6177146283629776562L},
            new long[]{3891663088081051271L, 2205460796984817483L},
            new long[]{3774974751412106303L, 8305726254820227338L},
            new long[]{2548985012555575236L, 4490656491009844577L},
            new long[]{8163104101231117915L, 3493251963477501241L},
            new long[]{4214528539198620540L, 4920855187574815014L},
            new long[]{1888548507038713106L, 5354955997588404048L},
            new long[]{1161595788381895631L, 5360145995007675815L},
            new long[]{1328390134675538736L, 3202648806554341833L},
            new long[]{7558605907711570139L, 461467623015870303L},
            new long[]{2153017743923701853L, 4935303867842353561L},
            new long[]{5934728598531004103L, 3859524609923305806L},
            new long[]{926562199821755665L, 5651480365597893727L},
            new long[]{8172671452365313789L, 1182282521549038374L},
            new long[]{3492616435307342868L, 4908177223534126902L},
            new long[]{9073528041206607092L, 7146899427041936064L},
            new long[]{7421961667893721857L, 1740959669998436441L},
            new long[]{1134078287044843770L, 6050502865815374860L},
            new long[]{6018400538076545466L, 6986767265072904605L},
    };

    private static final long[][] BB = new long[][]{
            new long[]{-1077786889433917806L, -6438239976976870091L},
            new long[]{-3425999014377863949L, -4971267447576192276L},
            new long[]{-8134160911886369372L, -3549485715741564386L},
            new long[]{-1604401586188710047L, -595065425775143207L},
            new long[]{-1961396471137372839L, -1224391716465051392L},
            new long[]{-662428126868950157L, -6719240632938270533L},
            new long[]{-6757146734515664354L, -1332880604307188759L},
            new long[]{-5665591655167095021L, -552512639895382874L},
            new long[]{-5425453310519798992L, -4764807607886234546L},
            new long[]{-7923462464308024602L, -6996869418842693698L},
            new long[]{-2627803348669580497L, -690027514420882580L},
            new long[]{-8975508984330728042L, -6706143184637298718L},
            new long[]{-685966272697144687L, -9112449543809303516L},
            new long[]{-3178711892762824189L, -2080123395545886790L},
            new long[]{-6656915972403883511L, -3406452624927714005L},
            new long[]{-2656119455817919518L, -5807913879580230284L},
            new long[]{-184421020953637716L, -52865394777574058L},
            new long[]{-3549358159896294910L, -7315457476993140991L},
            new long[]{-5002314034030534148L, -2254780749052481474L},
            new long[]{-4117540610463622035L, -7565284688701920235L},
            new long[]{-7692795146553721384L, -3419951546080881949L},
            new long[]{-3132577897411288926L, -927298256638187895L},
            new long[]{-2189104798122461954L, -6209724825617668946L},
            new long[]{-8248586104429521161L, -1958323715922762217L},
            new long[]{-2995915844521239885L, -561386478597246836L},
            new long[]{-2389438369443768047L, -1101066537570310506L},
            new long[]{-5518586679648313368L, -722557216006165969L},
            new long[]{-5492348610690688564L, -8135782825136052569L},
            new long[]{-8222109205770071467L, -5856962513070214275L},
            new long[]{-4011892671225622337L, -9083426490718727035L},
            new long[]{-5381833841171593456L, -1415484946223069221L},
            new long[]{-7381874614878496613L, -3892590022120513124L},
            new long[]{-6119815777778705401L, -3183819880227086793L},
            new long[]{-223770184015581203L, -4951104353953957598L},
            new long[]{-1074794532182083060L, -1790643851881103619L},
            new long[]{-7472564187589991781L, -2437221928888358285L},
            new long[]{-8097258065517558973L, -1212261915513454919L},
            new long[]{-731888167540259754L, -4342832098020884476L},
            new long[]{-7175690076241747419L, -581678377485880097L},
            new long[]{-8125551189230155596L, -3476304007696437463L},
            new long[]{-3730328163205330242L, -8055373298384594864L},
            new long[]{-4657071705626744850L, -1688938358337115895L},
            new long[]{-2102767451209167136L, -7064394283821719628L},
            new long[]{-2112713799114608553L, -5349650697474157253L},
            new long[]{-8577652288085140998L, -8017318277943477288L},
            new long[]{-2273530056670373458L, -5594770851427288997L},
            new long[]{-1980378233290503853L, -3632714961822563507L},
            new long[]{-4500893035672894069L, -7892215953568668272L},
            new long[]{-7764529292876686582L, -2487685905320154106L},
            new long[]{-8873555323020259632L, -947706694015509732L},
            new long[]{-2159119718792020015L, -2869021423586294555L},
            new long[]{-1168994855145457309L, -279460467736745490L},
            new long[]{-523621606843263281L, -709517371715184981L},
            new long[]{-7463081965006064232L, -5623431524725811727L},
            new long[]{-5327146857847134666L, -2035630376833405909L},
            new long[]{-7850093892139335421L, -4640471267625400856L},
            new long[]{-7882633107967592077L, -862789361213233091L},
            new long[]{-6685058106527157638L, -4702054943196772953L},
            new long[]{-147049316970591071L, -8531491265097988570L},
            new long[]{-8811530171025557509L, -7486900351192874247L},
            new long[]{-4166280856029968338L, -768156067521311730L},
            new long[]{-896292188770189491L, -6715282016920631216L},
            new long[]{-567623924647749997L, -1344339427852109201L},
            new long[]{-495602163903003192L, -7202098080758057461L},
            new long[]{-2589595054175507685L, -4937530024131917745L},
            new long[]{-2968030075541728517L, -2932632149706862219L},
            new long[]{-922772337494392640L, -2849594484495799882L},
            new long[]{-8891260929137133654L, -3021026812502408365L},
            new long[]{-6123580056988216266L, -7512490614767039535L},
            new long[]{-5110729183839746291L, -5118070338091884895L},
            new long[]{-3172726050318188026L, -1858652338975805875L},
            new long[]{-4944279660886838732L, -1797632584008407143L},
            new long[]{-6987763518284877887L, -700049122641770686L},
            new long[]{-1771805291518376455L, -6582344500990175144L},
            new long[]{-2044792441960746900L, -5858283034302419789L},
            new long[]{-3844630401987226135L, -4946145538994053170L},
            new long[]{-8235584868117458668L, -7837884863800064831L},
            new long[]{-9164567049242883434L, -6194102121551789634L},
            new long[]{-5936284107232204618L, -648673561517678070L},
            new long[]{-2095574162901623388L, -3341863338291092449L},
            new long[]{-3907717731907147841L, -2583376399142584803L},
            new long[]{-5383907276931865030L, -5008447042256699401L},
            new long[]{-6702187364136012863L, -7500851586751967045L},
            new long[]{-7901311664524130587L, -379679633128644490L},
            new long[]{-3889827293215946861L, -6425397734041032282L},
            new long[]{-4904585663636985962L, -3517169851672180356L},
            new long[]{-8962444967881016821L, -391847331763845995L},
            new long[]{-7061095453393848570L, -6299904343076371047L},
            new long[]{-698592100568981589L, -3028758838222800439L},
            new long[]{-1915999763435464460L, -3793800359948556734L},
            new long[]{-5288977644753949184L, -4282184926802485804L},
            new long[]{-1520688562785128087L, -3890471092803342690L},
            new long[]{-2689504220474091745L, -3924042357971508720L},
            new long[]{-2477993454730705331L, -1226375533721804729L},
            new long[]{-5860260279616425063L, -5015689911363190847L},
            new long[]{-1529851102715543476L, -4031064317817065697L},
            new long[]{-4288320508712720395L, -9204364423050974628L},
            new long[]{-4432488298316891193L, -6446692263039906445L},
            new long[]{-6989570559264814805L, -511780171642460900L},
            new long[]{-5908929077304789319L, -1517897704035389043L},
            new long[]{-2014585069977366889L, -8055168254050396319L},
            new long[]{-2135928588956827563L, -5410286585021109561L},
            new long[]{-8732203010684245935L, -6603247772062595627L},
            new long[]{-1849292507803806919L, -6868927762436869340L},
            new long[]{-690315307244353539L, -9216261586207778225L},
            new long[]{-9046470838957537043L, -3692175642638260524L},
            new long[]{-2849336854336780655L, -757177495696825646L},
            new long[]{-3956212607228258784L, -8974079582340482410L},
            new long[]{-8133941318169566846L, -8294911468408781766L},
            new long[]{-5969169045444731530L, -5069216482872404425L},
            new long[]{-2827379708146317246L, -3875414870150055540L},
            new long[]{-2147163809822344402L, -5503705030249407867L},
            new long[]{-9067852358989583163L, -4584952146086467230L},
            new long[]{-6840085960626611299L, -7578391706184042225L},
            new long[]{-59103173810750652L, -8774340977162321178L},
            new long[]{-5432329148676084322L, -6224718434171345329L},
            new long[]{-5595653814715644793L, -118344461537803256L},
            new long[]{-4717111542690233088L, -8942591647693990308L},
            new long[]{-7154767540152784987L, -7005696815048226343L},
            new long[]{-4927080215885751061L, -1211543899320485680L},
            new long[]{-2800768190194882690L, -999867045867337895L},
            new long[]{-2439722129507689022L, -7690152382875820303L},
            new long[]{-2490261590267453728L, -7330092305397261036L},
            new long[]{-4394089579839940768L, -2155491740122539399L},
            new long[]{-8500861791203326013L, -6665770895634430776L},
            new long[]{-5848297799153100970L, -2508178791421964323L},
            new long[]{-7887421781593492922L, -8627965635719165066L},
    };

    private static final long[][] LB = new long[][]{
            new long[]{4938052845462802919L, -1112860538937324691L},
            new long[]{5224847916143138345L, -110968566246918016L},
            new long[]{2870881921564166224L, -6610897520539300424L},
            new long[]{8724848476664741746L, -8913232367068722310L},
            new long[]{7047866099507432020L, -8542265177564204799L},
            new long[]{1548832727633365676L, -524146834054602260L},
            new long[]{8900556895393727580L, -2796717720597266530L},
            new long[]{2926117954856023717L, -2903747029672223154L},
            new long[]{5529751435088717872L, -6136400791841097279L},
            new long[]{3086071823591375012L, -7022258775698113855L},
            new long[]{3078061980031364091L, -924587067137000794L},
            new long[]{426472362917482L, -2190444296967387964L},
            new long[]{4764440158749863600L, -5619343248790550540L},
            new long[]{5305136426993825246L, -9165020564422176114L},
            new long[]{4683560106684912216L, -3052115384384993666L},
            new long[]{7166303011803219729L, -635714927569279747L},
            new long[]{1739346438057849836L, -3296235596741973377L},
            new long[]{1680768574906565015L, -7948489657280990118L},
            new long[]{3532600777747616737L, -1527331452456953137L},
            new long[]{3815536087218012389L, -6791280729897824953L},
            new long[]{1596772303577128947L, -3149913069724157504L},
            new long[]{5607981994920093974L, -700903567456543746L},
            new long[]{1274199160483696750L, -2712680446819404001L},
            new long[]{656385151142883457L, -1256965197772642752L},
            new long[]{9214656140749330452L, -4774535957587707446L},
            new long[]{3671102723193343887L, -910487649387061632L},
            new long[]{2224332354580294446L, -2735156278543104252L},
            new long[]{1685455039779546325L, -2890243106267788778L},
            new long[]{2630203006752620181L, -1973695833280514607L},
            new long[]{5146512108259602074L, -400044651082168613L},
            new long[]{5060502910335154532L, -6839113234901021430L},
            new long[]{9058727630230407913L, -5798085020998322238L},
            new long[]{2463659508688496867L, -4765732151309539574L},
            new long[]{2864430244033735451L, -1596154284471037573L},
            new long[]{4349428271229403356L, -6139026171037305629L},
            new long[]{955067037744597020L, -8519952472669631135L},
            new long[]{1323648769194122536L, -5344752252786633335L},
            new long[]{6985471842426664074L, -8877652597696841595L},
            new long[]{9079147473210487534L, -6755074097810013448L},
            new long[]{7402808384261932875L, -6406181604536085883L},
            new long[]{3064525066984135050L, -8678818061886213229L},
            new long[]{2744689294539078726L, -6267170506210307860L},
            new long[]{3402397113244700328L, -2905346946895161384L},
            new long[]{7493311987966489787L, -4899860203236319738L},
            new long[]{6895747912449763522L, -2584419846417885765L},
            new long[]{5631408189087997147L, -7941763661502962660L},
            new long[]{7252991816538892738L, -9212039604004201262L},
            new long[]{4745502885773495103L, -3899947555869475405L},
            new long[]{5121397346183343884L, -3746644758245730227L},
            new long[]{2166247174719872415L, -1827332528152565505L},
            new long[]{6478924746255446692L, -4993873048086956162L},
            new long[]{3415549683926966855L, -8561218107602608605L},
            new long[]{43359512139963307L, -1900743325036404601L},
            new long[]{2352463255685635669L, -4956189723638062552L},
            new long[]{1218078743391465647L, -8040353246077158894L},
            new long[]{6007902823378099583L, -6463495160897315601L},
            new long[]{822182216454386291L, -9080382177616450844L},
            new long[]{1294439918696411771L, -8899561939706962059L},
            new long[]{815174374749081123L, -1620071406118977815L},
            new long[]{7233943011652882673L, -2990033356283940604L},
            new long[]{4964135685071104702L, -186380796533641450L},
            new long[]{3724086369431116852L, -2305372940825307741L},
            new long[]{1308100536956441411L, -2635252271574470924L},
            new long[]{161031871365626957L, -8911524916291620955L},
            new long[]{6121157530455105265L, -3090926437426914345L},
            new long[]{5459488844451248514L, -2176567708568042152L},
            new long[]{3703285176448927671L, -2543427648630397017L},
            new long[]{4795711561455282184L, -7284724161946835219L},
            new long[]{5575775631138963798L, -6820023668066374280L},
            new long[]{937403184904750269L, -792700101038786944L},
            new long[]{4968750891340032410L, -3888481424859926357L},
            new long[]{5745509580129743979L, -4986233153615969826L},
            new long[]{6545680261293745779L, -2366299386216650717L},
            new long[]{6170905073779956389L, -5351938639684029606L},
            new long[]{5695124933951359411L, -4222414021740300408L},
            new long[]{2402236569313669720L, -2908830831815768620L},
            new long[]{4272353221404877962L, -3831171967518425353L},
            new long[]{7172070248142288018L, -5644732774264949781L},
            new long[]{3305559106087435675L, -2400692420923624190L},
            new long[]{7764062012086018881L, -3130097718360136230L},
            new long[]{5691460458739435043L, -6859699222675966964L},
            new long[]{1551509992028868809L, -7806905460301933530L},
            new long[]{5342836592656828282L, -8640210308532600423L},
            new long[]{4878507384650651477L, -954031770842823070L},
            new long[]{8653504654071056798L, -627678683582946992L},
            new long[]{974957545384993169L, -4779206227563325631L},
            new long[]{1647451733783781655L, -1839341753298660225L},
            new long[]{6021987110033317957L, -9205722348866937507L},
            new long[]{3665207584921736551L, -8528668503294565923L},
            new long[]{4178399221977994266L, -5746504038937172625L},
            new long[]{6660070269088620091L, -9206811882366334883L},
            new long[]{8660824229353865998L, -4173611969835709827L},
            new long[]{4942282281572391089L, -4355007204686822891L},
            new long[]{5567742312995437588L, -1519577694258448508L},
            new long[]{8603680600301497815L, -3426033485584151581L},
            new long[]{9063704331259095339L, -7276131100547320435L},
            new long[]{3740564127630884060L, -4722056984436636433L},
            new long[]{3427435806137303785L, -3754878576411628093L},
            new long[]{4819259685868109928L, -6127924417221536323L},
            new long[]{1517336079489843219L, -952198158790061083L},
            new long[]{3142772727900385200L, -3683695024292678536L},
            new long[]{9190190123327575764L, -7875146733586779229L},
            new long[]{2509026544286236895L, -5993934616906100230L},
            new long[]{2956125992463833872L, -66120208659539348L},
            new long[]{2506112256855827679L, -8723566085442069371L},
            new long[]{4391417269275904226L, -649432762745384998L},
            new long[]{91882253701044319L, -7652625867989014334L},
            new long[]{6123008370193800521L, -2760879489117471712L},
            new long[]{8328722371285418823L, -628871035284644031L},
            new long[]{8517458509134649564L, -1102742490349433572L},
            new long[]{7705456593999547843L, -3924070001519108281L},
            new long[]{1317864707402631841L, -5076968227876461610L},
            new long[]{6632264992486511241L, -2354434537666963219L},
            new long[]{838439849421565002L, -4538029196661933348L},
            new long[]{6504077864213532921L, -381476493955958178L},
            new long[]{1130795980562682556L, -3012068736640899939L},
            new long[]{1079269979191083110L, -8508822526619159820L},
            new long[]{791944973322677592L, -4694835376408089964L},
            new long[]{8783597431786744177L, -3097006640446830854L},
            new long[]{4227390300319501994L, -868301056790233712L},
            new long[]{3783689278736234476L, -3226970634664788638L},
            new long[]{6017296767921889515L, -7063404917535266972L},
            new long[]{840352340281930448L, -1308052356077320656L},
            new long[]{6562693523471857284L, -1888427337806939933L},
            new long[]{5619509049494806583L, -5730856443720128613L},
            new long[]{8813756648733341046L, -2612794059313980797L},
            new long[]{2271507738656123967L, -5041228179784661415L},
    };

    private static final long[][] BL = new long[][]{
            new long[]{-9009832514974872382L, 8694488933079543318L},
            new long[]{-3691861733904008782L, 5600982832525509781L},
            new long[]{-3490185148829523613L, 8501519609322018486L},
            new long[]{-6113287874764177732L, 236469981937940444L},
            new long[]{-7101419011631288283L, 527220710410318235L},
            new long[]{-6394961930735986283L, 4962755351907487250L},
            new long[]{-4664889015754339426L, 5947371706741749201L},
            new long[]{-8007169291297908011L, 7563401104620442443L},
            new long[]{-9140924646846314780L, 2408114411693773185L},
            new long[]{-3370093849904913334L, 2469951744623372380L},
            new long[]{-648081783485243340L, 816688965207381165L},
            new long[]{-5016485074163809311L, 8748342033551743879L},
            new long[]{-5932231621093530459L, 3919514885913890896L},
            new long[]{-7633804105625936967L, 842908982220659188L},
            new long[]{-5482170231686567531L, 3061273265285036392L},
            new long[]{-7523059969622373442L, 7290668801333470797L},
            new long[]{-7489761467225379877L, 3512250758929810951L},
            new long[]{-491000575309970181L, 8396806800978772730L},
            new long[]{-4465891103606025074L, 6959897314080182071L},
            new long[]{-1707945979104628731L, 2203231625794072044L},
            new long[]{-18166727701577536L, 1773313226392673558L},
            new long[]{-2168351989294432637L, 2576022240009943864L},
            new long[]{-7575707681686800502L, 3415593439201126683L},
            new long[]{-4137339489549980725L, 7423962500273066471L},
            new long[]{-1195166912568665671L, 6738889480748786410L},
            new long[]{-7243361373712105884L, 6268036184060324200L},
            new long[]{-6248708527028249080L, 1058376008693134217L},
            new long[]{-3964402910619586834L, 5670090823175188568L},
            new long[]{-8715931673057526793L, 4453065178626695298L},
            new long[]{-109014006608299108L, 8159690547170289655L},
            new long[]{-4239673921890992456L, 4758150252931157759L},
            new long[]{-3718972487579900100L, 8324168145812446153L},
            new long[]{-3934364629919049779L, 4652607240397656778L},
            new long[]{-7203272917723475714L, 453338459821791366L},
            new long[]{-8358250873972746051L, 8375355513778056259L},
            new long[]{-2673562122777756436L, 6348905416630030906L},
            new long[]{-1300168983185119163L, 5135853789496188656L},
            new long[]{-9119946941280630325L, 3600886319754415393L},
            new long[]{-149069699794870518L, 1707189225175379647L},
            new long[]{-4156390255403824870L, 6275243060535278210L},
            new long[]{-3548222525663581781L, 1607536397559899252L},
            new long[]{-2337638025760732138L, 5019686383986646910L},
            new long[]{-7391973982730122685L, 836208399496287786L},
            new long[]{-743110356093300779L, 6758257007841508714L},
            new long[]{-3974033489131196650L, 6413552292992803017L},
            new long[]{-5065843567244834270L, 8623527875274521159L},
            new long[]{-2020162905327112236L, 4645783625186846968L},
            new long[]{-2754625910625703492L, 4486358694357233092L},
            new long[]{-3933575691377321109L, 757649284986745002L},
            new long[]{-1532140533358564239L, 7089629366928159975L},
            new long[]{-5421452132072636236L, 3964051179785850532L},
            new long[]{-3796379516751140280L, 4919998129425254553L},
            new long[]{-1087518414434446764L, 171226273334418049L},
            new long[]{-2858547306473530981L, 8500729757697044222L},
            new long[]{-499996046038278353L, 3128708242759964790L},
            new long[]{-8529530604143760824L, 2813627840850673523L},
            new long[]{-4976745782106998671L, 1130108705915519531L},
            new long[]{-8722038070371743736L, 5069712401191397000L},
            new long[]{-4045731301988708393L, 1854301313838946701L},
            new long[]{-1326155059356051795L, 3632865449770465324L},
            new long[]{-3102113198497987720L, 8934442886158087333L},
            new long[]{-2391904370961764274L, 2394652198710217016L},
            new long[]{-782516216654046704L, 414290996110758542L},
            new long[]{-3771119756983115845L, 1431159673088689597L},
            new long[]{-6325512738382185923L, 1968676328687342043L},
            new long[]{-1801078389609640074L, 5106851581305975253L},
            new long[]{-2426379019363095686L, 3277780702799215153L},
            new long[]{-5798677285897976794L, 4463633552160625272L},
            new long[]{-5338891584304502645L, 2908393109171063422L},
            new long[]{-231170023388640112L, 2483392747880040491L},
            new long[]{-4243128016619231458L, 1594408558724409732L},
            new long[]{-7965289033988475524L, 8719740668871662583L},
            new long[]{-6344696156857451255L, 1206312179808383982L},
            new long[]{-8843558104384566786L, 583998391103535784L},
            new long[]{-8212323015622533631L, 7825440863528030415L},
            new long[]{-2278469059128768620L, 6770007956436583840L},
            new long[]{-638409689853897338L, 3347886734071644889L},
            new long[]{-9145692987565323081L, 4191355651793559793L},
            new long[]{-7527517948080733992L, 7267195112779590235L},
            new long[]{-667612773043311457L, 4633650966277848848L},
            new long[]{-4478377459548921810L, 3192507487986770320L},
            new long[]{-5167550750898319210L, 108561762863341121L},
            new long[]{-1347491772541834682L, 7151171217806299439L},
            new long[]{-7012059657912112591L, 7900884602376545703L},
            new long[]{-5788742824795472981L, 6907985480250458062L},
            new long[]{-172195140842364628L, 7761197493111883797L},
            new long[]{-2300220882446049170L, 1426456069768658221L},
            new long[]{-5448567562978520078L, 953419186548669869L},
            new long[]{-7149270422981396078L, 6762211782221097485L},
            new long[]{-343501639263802343L, 7360407688496015979L},
            new long[]{-3048393313960745061L, 7470291169147646772L},
            new long[]{-7582478324225813607L, 6991606816658101701L},
            new long[]{-3018724762173276841L, 3360922974664432414L},
            new long[]{-8104813226534172814L, 2147421058388262726L},
            new long[]{-3560939091790244584L, 4774281009466642295L},
            new long[]{-1139707712757331619L, 3511095636752942138L},
            new long[]{-4612159019812992255L, 7816416475069435706L},
            new long[]{-4954372971313475428L, 7836013546061003912L},
            new long[]{-2955059401234927079L, 8092139130469183134L},
            new long[]{-3738365814539023402L, 4895821398006136489L},
            new long[]{-6492028519628824982L, 7311215580212285343L},
            new long[]{-2099500277564604050L, 1187542519834094392L},
            new long[]{-486732686173537615L, 797741681794783281L},
            new long[]{-2408433395141932476L, 5458766989599115096L},
            new long[]{-5765796430629386676L, 7325256442189851117L},
            new long[]{-5291731438132869323L, 3353520549483209142L},
            new long[]{-432782776429456328L, 4420095282174331738L},
            new long[]{-6265109630488607742L, 1832400718146608991L},
            new long[]{-1635452057236082872L, 5373912354368559629L},
            new long[]{-5483450243581663425L, 7018729429181217728L},
            new long[]{-3966487726810982006L, 1222006632577744776L},
            new long[]{-8694327616358472824L, 6231336019755467135L},
            new long[]{-2226638326094960843L, 2077506401863813405L},
            new long[]{-6350673710600467343L, 1372287876326227335L},
            new long[]{-7296299962344841436L, 3311799846515400518L},
            new long[]{-4328920826880566100L, 7108433832846851768L},
            new long[]{-5271239936629276752L, 1124929441378051967L},
            new long[]{-7263321229706748064L, 4714523774683450170L},
            new long[]{-5137894796283683636L, 3927494822476270953L},
            new long[]{-3974314763851955650L, 5530192567700922638L},
            new long[]{-3629570186737227262L, 472776863014999401L},
            new long[]{-9076435870019024285L, 7612034475907026514L},
            new long[]{-3046815687715765091L, 7104681352218397009L},
            new long[]{-7248984242515407180L, 4512414616076518432L},
            new long[]{-3621486997122488218L, 616168377393900922L},
            new long[]{-2129110859843987550L, 5861528353498900913L},
            new long[]{-5198080888721134248L, 874601940727909363L},
    };

    @Test
    void init() {
        Assertions.assertThrows(IllegalAccessException.class, () -> new Check());
    }

    @Test
    void m7() {
        for (int i = 0; i < 127; i++) {
            final long[] bb = BB[i];
            final long[] ll = LL[i];
            final long[] lb = LB[i];
            final long[] bl = BL[i];
            Assertions.assertEquals(i, Check.m7(bb[0], bb[1]));
            Assertions.assertEquals(i, Check.m7(ll[0], ll[1]));
            Assertions.assertEquals(i, Check.m7(lb[0], lb[1]));
            Assertions.assertEquals(i, Check.m7(bl[0], bl[1]));
        }
    }
}
