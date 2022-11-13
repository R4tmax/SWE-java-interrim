package cz.vse.java.kadm09.jfx.gatekeepermk2.gameworld;

import cz.vse.java.kadm09.jfx.gatekeepermk2.enemies.TheBrute;
import cz.vse.java.kadm09.jfx.gatekeepermk2.enemies.TheHag;
import cz.vse.java.kadm09.jfx.gatekeepermk2.enemies.TheMatriarch;
import cz.vse.java.kadm09.jfx.gatekeepermk2.items.Consumable;
import cz.vse.java.kadm09.jfx.gatekeepermk2.items.ConsumableType;
import cz.vse.java.kadm09.jfx.gatekeepermk2.items.MoneyLoot;
import cz.vse.java.kadm09.jfx.gatekeepermk2.knight.TheKnight;

public class Map {
    private final Room [][] gameArea = new Room[5][5];



    public Room getCurrentPosition(int x, int y) {
        return gameArea[x][y];
    }

    public String presentPosition(TheKnight player) {
        return getCurrentPosition(player.getPosition().getHorizontal(),player.getPosition().getVertical()).getName()
                + "\n"
                + getCurrentPosition(player.getPosition().getHorizontal(),player.getPosition().getVertical()).getDescription();
    }

    public void fillMap () {

        //First row - Northernmost
        gameArea[0][0] = new Room("Northwestern rocky hills", """
                You arrive at the small hill overseeing the forest underneath, due south.
                You can't see much around, but a beautiful sight of your kingdom can be seen due west.
                Continuing north or west is not possible from here, and probably ill advised, given the task at hand.
                But, a path can be followed to the east.
                You notice some abandoned military supplies nearby, realization dawning upon you, you are close to the
                heart of the beast.
                """, false , new Consumable("Armorsmith tools",1, ConsumableType.ARMOR_BOOST), null, RoomType.RECON);
        gameArea[0][1] = new Room("Foot of the mountain", """
                Up north, enormous rock blocks your way. A black monolith of basalt and marble.
                However you can see, despite the rocky terrain, the trees due south.
                There is also, at least it looks that way, a small path running alongside the foot of the mountain.
                Heading either west or east. To your dismay, what seems to be bloodstains and human remains become
                frequent, as you scout the eastern path. Be wary.
                Somehow, Blue Herbs are growing the barren earth here.
                """, false , new Consumable("Blue herb",25,ConsumableType.MANA_FILL), null, RoomType.RECON);
        gameArea[0][2] = new Room("MONSTER NEST", "END OF THE LINE", true , null, new TheMatriarch("Matriarch",1500, 13), RoomType.HOSTILE);
        gameArea[0][3] = new Room("Chasm", """
                Whether by chance or fate, you manage to stay on track in spite of hard terrain.
                You can see the sun behind you, illuminating narrow path leading back to the forests.
                It is possible to head either west or east. But the entry to the western path is littered
                by skeletons of various animals, picked clean.
                """, false , null, null, RoomType.RECON);
        gameArea[0][4] = new Room("Northeastern rocky hills", """
                You arrive at the small hill overseeing the forest underneath, due south.
                You can't see much around, but you hear a rushing river due east.
                Continuing north or east is not possible from here, and probably ill advised, given the task at hand.
                But, a path can be followed to the west.
                You notice some abandoned military supplies nearby, realization dawning upon you, you are close to the
                heart of the beast.
                """, false , new Consumable("Armorsmith tools",1,ConsumableType.ARMOR_BOOST), null, RoomType.RECON);

        //Second row
        gameArea[1][0] = new Room("Edge of the forest", """
                You walk amongst the thinly space trees and admire the view to the west.
                You cannot quite get there, as a narrow cliff block your way. But you see distant cities
                and castles on the horizon. Places where you come from and for which you fight.
                Your reminiscing is interrupted however, by a vague sense of darkness coming form the south.
                Up north, hills rise up, while eastern view is blocked by the trees.
                Blue herbs are growing here.
                """, false , new Consumable("Blue Herb",25,ConsumableType.MANA_FILL), null, RoomType.RECON);
        gameArea[1][1] = new Room("Deep forest", """
                As you make your way through the forrest, you feel cold and isolated.
                Even in the middle of the noon, the sun doesn't reach you here.
                However, you notice that the trees get more spaced out due north and west.
                You get a sudden impression like it might be worth looking around.
                """, false , new MoneyLoot("Rare Herbs", 200), null, RoomType.RECON);
        gameArea[1][2] = new Room("Deep forest", """
                As you make your way through the forrest, you feel cold and isolated.
                Even in the middle of the noon, the sun doesn't reach you here.
                You are not quite sure where exactly your are.
                """, false , null, null, RoomType.RECON);
        gameArea[1][3] = new Room("Deep forest", """
                You have lost track of where exactly you are.
                You can make vague outline of the sun between the trees. Allowing you to keep a sense of direction.
                After stumbling around a bit, you notice a barrow, no one has been here for quite a while.
                """, false , new MoneyLoot("Silver Trinkets", 350), null, RoomType.RECON);
        gameArea[1][4] = new Room("Boggy forrest", """
                This place looks almost surreal. You see a huge marsh opening to east and south.
                While mountain range looms to the north. Western direction is saddled by trees and bushes.
                Flies and mosquitoes constantly annoy you. You can feel some sort of darkness creeping out of the
                south. Be careful.
                """, false , null, null, RoomType.RECON);

        //third row

        gameArea[2][0] = new Room("THE STRANGE CLEARING", """
                Phew, the monstrosity is dead.
                You have little idea what that thing was. But you are glad you made it out.
                The creature, even dead, looks menacing and out of this world. It still could be, for all you know.
                The Kings Road is safe now, for the time being.
                You get your bearings. Road is due South, and forests lie north-east.
                You notice that the way west would take you over a cliff, the drop is too high to brave.
                """, false , null, new TheBrute("Brute",1000,8,1000), RoomType.HOSTILE);
        gameArea[2][1] = new Room("Bush-filled plain", """
                It feels, like someone lives nearby. You notice that a lot of the bushes and small trees
                in the area have damaged bark and branches, as if something regularly smashed through here.
                As you head west, the damage gets more and more pronounced. You could head elsewhere, you know.
                But what is this?! Are those? Gems?
                """, false , new MoneyLoot("Sack full of Gems",500), null, RoomType.RECON);
        gameArea[2][2] = new Room("Edge of the forrest", """
                You are on the edge of the local forests.
                It feels like you are being watched by something from the north.
                But you can't quite make out anything else, than rows of trees from that direction.
                Your view to both the east and the west is obscured by trees, but the terrain looks passable.
                After some wandering around you find a dead caravan guard, desperately clutching a blue vial.
                """, false , new Consumable("Blessed Mana Potion",50,ConsumableType.MANA_FILL), null, RoomType.RECON);
        gameArea[2][3] = new Room("Edge of the marsh", """
                You made your way to what seems to be some sort of a bog.
                Short colorful flowers are scattered everywhere, but soil gives way under your weight.
                After some deliberation you find a path through all of it. But you are not exactly comfortable.
                You don't see much, but it is obvious that danger is ahead due east.
                Fortunately the bog gives way in all other directions.
                """, false , null, null, RoomType.RECON);
        gameArea[2][4] = new Room("THE DEEP SWAMP", """
                Creature which you decided to dub 'The Hag' lies dead at your feet.
                It almost looks like the ground began to reclaim the body.
                Something tells you, that the corpse won't be here come next week.
                You cannot see anything from where you are standing, aside from the fact,
                that swamp is impassable due east, you need to head somewhere else.
                As you look around one last time, some herbs catch your attention.
                """, false , new Consumable("Magical herbs",50,ConsumableType.MANA_FILL), new TheHag("Hag", 625,7,750), RoomType.HOSTILE);

        //fourth row
        gameArea[3][0] = new Room("Dirt road", """
                Same as along the Kings Road, which lies to your south now.
                The area seems... haunted somehow. As if you intruded on someones property.
                As you think about going north, you feel the need to hold the hilt of your sword.
                You can still make out village buildings to the east.
                """, false , null, null, RoomType.RECON);
        gameArea[3][1] = new Room("Wheat field", """
                The village wheat fields are tended by only a handful of locals.
                Most of the people you see are guards.
                The area opens up to the west, towards the roads.
                in the north-eastern direction forests and open meadows lie.
                Someone has forgotten bread on one of the stone fences, interesting.
                """, false , new Consumable("Nourishing bread",50,ConsumableType.HEALTH_FILL), null, RoomType.RECON);
        gameArea[3][2] = new Room("Meadow", """
                With village to your south, you stand in the middle of tranquil meadow.
                Village fields are to your left, while the path seems to wind down to the other side, east.
                In front of you, pine forests begin rising up, as to greet you in this land.
                You recognize some of the local flora as valuable, often used in preparing spirits, perhaps worth foraging?
                """, false , new MoneyLoot("Rare herb",50), null, RoomType.RECON);
        gameArea[3][3] = new Room("Lowland", """
                After not seeing anything interesting for a while, you realise
                that you have entered some sort of plateau or a valley, perhaps
                that is considerably lower than the rest of the area. You don't see much.
                But all directions seem accessible enough.
                """, false , null, null, RoomType.RECON);
        gameArea[3][4] = new Room("River meander", """
                As southern fields disappear under the horizon, you start walking up north, with the river to your right.
                Suddenly, the air starts getting heavy. Hairs on your neck start standing up in attention.
                Something is wrong up north, perhaps heading west first would be prudent?
                """, false , null, null, RoomType.RECON);

        //fifth row
        gameArea[4][0] = new Room("The Kings Road", """
                The Kings Road. Your company arrived through here couple of days ago.
                You turn northward, and notice couple of smashed supply carts bearing
                Kings insignia. They are reduced to splinters, perhaps still looking around though?
                You know, that the Village is straight east from the last crossroad, and the attacks were reported due north.
                """, false , new Consumable("Whetting stone",5,ConsumableType.DAMAGE_BOOST), null, RoomType.RECON);
        gameArea[4][1] = new Room("The Tavern", """
                A bunch of locals from the surrounding fields are drinking
                in front of what seems to be sturdy built tavern.
                You get the impression that you could rest here, if you needed to.
                Kings Road is further westward, while golden field lies to your north.
                You can still make out insignias on the tents in the Village down east.
                """, false , null, null, RoomType.REST_AREA);
        gameArea[4][2] = new Room("Village courtyard", """
                Courtyard of the local village.
                It is distant, isolated, but surprisingly robust and nice looking.
                Close proximity to the Kings Road guarantees good business.
                But fear is in the eyes of the locals.
                Scout of your expedition is standing near the the tents and the crates marked with
                insignias of the King.
                In front of you, to the north, mountain range stands erect.
                Most of the Villagers are either to the east or west, at the inn, or the market.
                """, false , new Consumable("Blessed Health potion",200 ,ConsumableType.HEALTH_FILL), null, RoomType.TALKABLE);
        gameArea[4][3] = new Room("The Market", """
                The market is full of people, maybe you could talk with them?
                Further east, a vague outline of the river can be seen.
                Some of the villagers are heading back to the village center, to the west.
                """, false , null, null, RoomType.TRADABLE);
        gameArea[4][4] = new Room("River bank", """
                The village commotion can no longer be heard.
                Instead a gentle rush of the river and a singing birds make your company.
                You should head north, you think, when some weird herbs catch your attention.
                Maybe you could try picking them up?
                """, false , new Consumable("Blue herb",25,ConsumableType.MANA_FILL), null, RoomType.RECON);

    }
}
