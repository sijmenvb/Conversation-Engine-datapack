# always run by the NPC

# this function checks wich NPC the player has clicked

execute at @s[tag=lab_rat] run function conversation_engine:messages/lab_rat/start
execute at @s[tag=lab_ape] run function conversation_engine:messages/lab_ape/start