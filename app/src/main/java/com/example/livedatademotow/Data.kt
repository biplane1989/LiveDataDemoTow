package com.example.livedatademotow

object Data {

    var id: Int = 0
    fun getData(): ArrayList<Note> {
        var list = ArrayList<Note>()

        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))

        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))
        list.add((Note(id++, "orange", true)))

        return list
    }

}