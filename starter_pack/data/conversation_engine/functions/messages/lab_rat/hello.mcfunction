# run as the player

# message id: 1

# give the choices
tellraw @s [{"text":"\n\n\n[lab rat]","color":"#8F7833"},{"text":"hello I am lab rat\n","color":"white"},{"text":"hello","color":"#A8DFFF","clickEvent":{"action":"run_command","value":"/trigger CE_trigger set 2"}}]


# update the last run node
scoreboard players operation @s CE_current_node = @s CE_trigger

